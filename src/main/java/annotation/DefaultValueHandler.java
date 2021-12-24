package annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultValueHandler {

    Logger logger = Logger.getLogger(DefaultValueHandler.class.getName());

    void handle(Object target) {
        Field[] declared = target.getClass().getDeclaredFields();
        for (Field field : declared) {
            DefaultValue present = field.getAnnotation(DefaultValue.class);
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(target);
                if (fieldValue == null) {

                    Optional<Constructor<?>> ofStringConstructor = getOfStringConstructor(field.getType());
                    if (ofStringConstructor.isPresent()) {
                        logger.log(Level.INFO, String.format("Field '%s' of type %s has constructor of String.class, build...", field.getName(), field.getType().getSimpleName()));
                        Object fieldNewValue = ofStringConstructor.get().newInstance(present.value());
                        field.set(target, fieldNewValue);
                    } else {
                        logger.log(Level.INFO, "Class has no constructors of String.class, try to find " +
                                "factory-like methods");
                        for (Method m : field.getType().getDeclaredMethods()) {
                            m.setAccessible(true);
                            Class<?>[] parameters = m.getParameterTypes();
                            //1 параметр метода и это строковый тип, обязательно статический вызов иначе смысл создавать пустой инстанс
                            if (parameters.length == 1
                                    && m.getReturnType().equals(field.getType())
                                    && Modifier.isStatic(m.getModifiers())) {
                                logger.log(Level.INFO, "Found field class factory method '" + m.getName() + "'");
                                Object fieldNewValue = m.invoke(null, present.value());
                                field.set(target, fieldNewValue);
                                //искать больше смысла нет. Выход
                                break;
                            }
                        }
                    }
                }
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                logger.log(Level.WARNING, "Something went wrong while set default value " + present.value(), e);
                e.printStackTrace();
            }
        }
    }

    private Optional<Constructor<?>> getOfStringConstructor(Class<?> type) {
        return Arrays.stream(type.getDeclaredConstructors())
                .filter(constructor -> {
                    int parameterCount = constructor.getParameterCount();
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    if (parameterCount == 1) {
                        return parameterTypes[0].equals(String.class);
                    }
                    return false;
                })
                .findFirst();
    }
}
