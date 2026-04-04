package com.example.datetime;

import java.time.LocalDate;

public record SimpleDateInterval(LocalDate startDate, LocalDate endDate) {
    public SimpleDateInterval {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("End date is before start date");
        }
    }
}
