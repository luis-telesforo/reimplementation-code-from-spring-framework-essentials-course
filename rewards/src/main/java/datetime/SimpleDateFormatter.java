package datetime;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;
import static java.time.ZoneId.of;
import static java.time.format.DateTimeFormatter.ISO_DATE;

public class SimpleDateFormatter {
    public static final ZoneId UTC_ZONE_ID = of("UTC+00");

    public static LocalDate today() {
       return now(UTC_ZONE_ID);
    }

    public static LocalDate parseDate(String date) {
        return ISO_DATE.parse(date, LocalDate::from);
    }

    public static String formatDate(LocalDate date) {
        return ISO_DATE.format(date);
    }
}
