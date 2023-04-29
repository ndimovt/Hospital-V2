package io.github.ndimovt.hospitalv2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DateAndTime {
    public static String dateTime;
    static {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        dateTime = currentDateAndTime.format(format);
    }
}
