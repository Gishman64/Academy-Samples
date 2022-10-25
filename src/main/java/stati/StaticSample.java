package stati;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StaticSample {

    private static final C c = new C("field");
    private B b;

    public abstract class A {
        private final String f;

        protected A(String f) {
            this.f = f;
        }

        public abstract int f1();
    }

    class B extends A {

        protected B(String f) {
            super(f);
        }

        @Override
        public int f1() {
            class LocalClassForBlock {
                public int veryExpensive() {
                    return 1;
                }
            }
            return new LocalClassForBlock().veryExpensive();
        }

        public class BNested {

        }
    }

    public static class C {
        public String field;

        public C(String field) {
            this.field = field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

    static class F {
        public F() {
            System.out.println("F is created");
        }
//        private int i;
//
//        F(int i) {
//            this.i = i;
//        }

        //        protected void m() {
//            System.out.println(this.i);
//        }
        public class FNested {
            private final String field;

            public FNested(String field) {
                this.field = field;
            }
        }
    }

    static class F1 extends F implements S, S1 {
        public F1() {
            System.out.println("F1 is created");
        }

        private void fun() {
            S.super.f1();
        }

        @Override
        public void f1() {
            S.super.f1();
        }
    }

    interface S {
        default void f1() {
            System.out.println("s");
        }
    }

    interface S1 {
        default void f1() {
            System.out.println("s1");
        }
    }

    @Getter
    class Employee {
        private long depId;
        private long salary;
    }

    @Getter
    class Departament {
        private long id;
        private String name;
    }

    public static void main(String[] args) {
        //вывести все департаменты, в которых работает не менее N человек с зарплатой большей или равной M
        var employees = new HashSet<Employee>();
        var depts = new HashSet<Departament>();

        char ic = '3';

        int project = ic;

        var n = 2;
        var m = 2000;

        var candidates = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepId))
                .entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .filter(employee -> employee.getSalary() > m)
                        .count() > n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        List<String> deptNames = depts.stream().filter(it -> candidates.contains(it.getId()))
                .map(Departament::getName).collect(Collectors.toList());

        new F1().fun();

        new F().new FNested("");

        var f = new F1() {
            @Override
            public void f1() {
                Runnable r = () -> {
                    System.out.println("ochko");
                };
                ExecutorService e = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                synchronized (this) {

                }
                System.out.println("unbelievable");
                super.f1();

                Function<Object, Function<Object, String>> carry = a -> b -> new StringBuilder().append(a).append(b).toString();

                System.out.println(carry.apply("a").apply("b"));

                Function before = (Object o) -> {
                    System.out.println("Передаем " + o);
                    return o;
                };

                final Object apply = Function.identity()
                        .compose(o -> {
                            System.out.println("BEfore");
                            return o;
                        })
                        .andThen(o -> {
                            System.out.println("After");
                            return o;
                        }).apply("new Object()");

                System.out.println(apply);
                Function<Object, String> f = Object::toString;

                Stream.of(new Object(), new Object(), new Object())
                        .map(Object::toString);
            }
        };
        f.f1();
        LocalDateTime i = LocalDateTime.now();
//
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        System.out.println(formatter.format(i));
    }


    private void demo() {
        var database = new Object();

//        Optional.ofNullable("123") //представим что тут неопределенный вызов
//                .map(String::length)
//                .orElse(database.writeAndGetToDataBase()); //чем это чревато?
    }
}
