package xml.util;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

public class XMLLocalDateConverter {
    public static LocalDateTime parse(String local) {
        return LocalDateTime.parse(local);
    }

    public static String print(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    private static XMLGregorianCalendar toCalendar(LocalDateTime localDateTime) {
        return null;//stub
    }
}
