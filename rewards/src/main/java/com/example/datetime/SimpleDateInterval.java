package com.example.datetime;

import java.time.LocalDate;

/**
 * Holds a date interval.
 *
 * @param startDate the start date.
 * @param endDate the end date.
 */
public record SimpleDateInterval(LocalDate startDate, LocalDate endDate) {
    /**
     * Only valid intervals are allowed.
     *
     * @param startDate the start date.
     * @param endDate the end date.
     */
    public SimpleDateInterval {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("End date is before start date");
        }
    }
}
