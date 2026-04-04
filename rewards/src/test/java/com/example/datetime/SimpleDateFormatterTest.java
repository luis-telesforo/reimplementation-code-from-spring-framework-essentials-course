package com.example.datetime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static com.example.datetime.SimpleDateFormatter.*;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link SimpleDateFormatter}
 */
@DisplayName("SimpleDateFormatter")
class SimpleDateFormatterTest {

    @Test
    @DisplayName("Parsing dates.")
    void parseDatePositiveTest() {
        assertEquals(parseDate("1920-01-03"), of(1920, 1, 3));
    }

    @Test
    @DisplayName("Parsing requires 2 digits for months. ")
    void parseDateMonthTest() {
        DateTimeParseException dateTimeParseException =
                assertThrows(DateTimeParseException.class, () -> parseDate("1920-1-03"));
        assertEquals(5, dateTimeParseException.getErrorIndex());
    }

    @Test
    @DisplayName("Parsing requires 2 digits for months. ")
    void parseDateDaysTest() {
        DateTimeParseException dateTimeParseException =
                assertThrows(DateTimeParseException.class, () -> parseDate("1920-01-3"));
        assertEquals(8, dateTimeParseException.getErrorIndex());
    }

    @Test
    @DisplayName("Parsing requires 4 digits for years. ")
    void parseDateYearsTest() {
        DateTimeParseException dateTimeParseException =
                assertThrows(DateTimeParseException.class, () -> parseDate("99-01-03"));
        assertEquals(0, dateTimeParseException.getErrorIndex());
    }

    @Test
    void parseDateStrictMonthTest() {
        DateTimeParseException dateTimeParseException =
                assertThrows(DateTimeParseException.class, () -> parseDate("1920-13-03"));
        assertEquals("Text '1920-13-03' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13", dateTimeParseException.getMessage());
    }

    @Test
    void parseDateStrictDaysTest() {
        DateTimeParseException dateTimeParseException =
                assertThrows(DateTimeParseException.class, () -> parseDate("1920-01-32"));
        assertEquals("Text '1920-01-32' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 32", dateTimeParseException.getMessage());
    }

        @Test
    void formatDateTest() {
        assertEquals("1920-01-03", formatDate(of(1920, 1, 3)));
    }
}