package com.example.database;

import com.example.money.MonetaryAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static com.example.database.Dining.createDining;
import static com.example.datetime.LocalDateFormatUtil.today;
import static com.example.money.MonetaryAmountFormatUtil.parseAsMonetaryAmount;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Tests for {@link Dining}.
 */
@DisplayName("Dining tests")
class DiningTests {

    @Test
    @DisplayName("createDining sets date to today")
    void testCreateDiningWithToday() throws ParseException {
        String amountStr = "$45.99";
        String creditCard = "1234567890123456";
        String merchant = "M123456789";
        Dining dining = createDining(amountStr, creditCard, merchant);
        MonetaryAmount expectedAmount = parseAsMonetaryAmount(amountStr);
        assertEquals(expectedAmount, dining.amount());
        assertEquals(creditCard, dining.creditCardNumber());
        assertEquals(merchant, dining.merchantNumber());
        assertEquals(today(), dining.date());
    }

    @Test
    @DisplayName("createDining with specified date should set correct date")
    void testCreateDiningWithSpecifiedDate() throws ParseException {
        String amountStr = "$100.50";
        String creditCard = "9876543210987654";
        String merchant = "M987654321";
        int month = 7;
        int day = 4;
        int year = 2023;
        Dining dining = createDining(amountStr, creditCard, merchant, month, day, year);
        MonetaryAmount expectedAmount = parseAsMonetaryAmount(amountStr);
        assertEquals(expectedAmount, dining.amount());
        assertEquals(creditCard, dining.creditCardNumber());
        assertEquals(merchant, dining.merchantNumber());
        assertEquals(of(year, month, day), dining.date());
    }

    @Test
    @DisplayName("createDining should throw ParseException for invalid amount")
    void testCreateDiningWithInvalidAmount() {
        String invalidAmount = "not-a-number";
        String creditCard = "1234567890123456";
        String merchant = "M123456789";
        ParseException parseException = assertThrowsExactly(ParseException.class,
                () -> createDining(invalidAmount, creditCard, merchant));
        assertEquals("Unparseable number: \"not-a-number\"", parseException.getMessage());

    }
}
