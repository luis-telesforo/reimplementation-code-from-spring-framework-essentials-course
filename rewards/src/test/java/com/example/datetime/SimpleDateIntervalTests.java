package com.example.datetime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Tests for {@link SimpleDateInterval}.
 */
@DisplayName("Tests for SimpleDateInterval")
public class SimpleDateIntervalTests {
    static final LocalDate PREVIOUS_DATE = of(2022, 1, 1);
    static final LocalDate POSTERIOR_DATE = of(2022, 1, 5);

    @Test
    @DisplayName("Valid interval creation")
    void testCreation() {
        SimpleDateInterval simpleDateInterval =
                new SimpleDateInterval(PREVIOUS_DATE, POSTERIOR_DATE);
        assertFalse(simpleDateInterval.startDate().isAfter(simpleDateInterval.endDate()),
                "Start date should be before end date");
    }

    @Test
    @DisplayName("Invalid interval creation")
    void testInvalidCreation() {
        IllegalArgumentException illegalArgumentException =
                assertThrowsExactly(IllegalArgumentException.class,
                        () -> new SimpleDateInterval(POSTERIOR_DATE, PREVIOUS_DATE));
        assertEquals("End date is before start date",
                illegalArgumentException.getMessage());
    }
}
