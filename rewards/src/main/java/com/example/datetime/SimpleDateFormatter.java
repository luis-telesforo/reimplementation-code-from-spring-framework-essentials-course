package com.example.datetime;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.now;
import static java.time.ZoneId.of;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

/**
 * Centralizes the use of {@link DateTimeFormatter#ISO_LOCAL_DATE},
 * to simplify further refactoring of date format if needed.
 */
public class SimpleDateFormatter {
    /**
     * This application assumes the date is the one from {@code UTC+00}.
     */
    private static final ZoneId UTC_ZONE_ID = of("UTC+00");

    /**
     * @return The current date in  {@code UTC+00}.
     */
    public static LocalDate today() {
       return now(UTC_ZONE_ID);
    }

    /**
     * Parses the given {@link String} using {@link DateTimeFormatter#ISO_LOCAL_DATE}.
     * @param date The date to be parsed
     * @return The {@link LocalDate} represented by the given date if it is in the right format.
     * @see DateTimeFormatter#parse
     */
    public static LocalDate parseDate(String date) {
        return ISO_LOCAL_DATE.parse(date, LocalDate::from);
    }

    /**
     * Formats the given {@link LocalDate} in the {@code ISO-8601} format.
     * @param date The date to be formatted.
     * @return The formatted date.
     * @see <a href="https://es.wikipedia.org/wiki/ISO_8601">ISO-8601 definition</a>
     * @see DateTimeFormatter#format
     */
    public static String formatDate(LocalDate date) {
        return ISO_LOCAL_DATE.format(date);
    }
}
