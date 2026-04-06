package com.example.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static com.example.money.MonetaryAmountFormatter.formatToCurrency;
import static com.example.money.MonetaryAmountFormatter.parseAsMonetaryAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonetaryAmountFormatterTest {

    @Test
    @DisplayName("Format no decimal MonetaryAmounts")
    public void testFormatNoDecimalMonetaryAmounts() {
        MonetaryAmount amount1 = new MonetaryAmount(2);
        assertEquals("$2.00", formatToCurrency(amount1));
    }

    @Test
    @DisplayName("Format decimal MonetaryAmounts")
    public void testFormatDecimalMonetaryAmounts() {
        MonetaryAmount amount3 = new MonetaryAmount(10.01);
        assertEquals("$10.01", formatToCurrency(amount3));
    }

    @Test
    @DisplayName("Format decimal MonetaryAmounts")
    public void testFormatDecimalMonetaryAmount() {
        MonetaryAmount monetaryAmount = new MonetaryAmount(10.01);
        assertEquals("$10.01", formatToCurrency(monetaryAmount));
    }

    @Test
    @DisplayName("Parse no decimal money strings")
    public void testParseNoDecimalAsMonetaryAmount() throws ParseException {

        MonetaryAmount amount2 = parseAsMonetaryAmount("$43");
        assertEquals(43, amount2.asDouble());
    }

    @Test
    @DisplayName("Parse zero decimal money strings")
    public void testParseZeroDecimalMonetaryAmountValidInput() throws ParseException {
        MonetaryAmount amount2 = parseAsMonetaryAmount("$43.00");
        assertEquals(43, amount2.asDouble());
    }
    @Test
    @DisplayName("Parse decimal money string")
    public void testParseDecimalMonetaryAmountValidInput() throws ParseException {
        MonetaryAmount amount2 = parseAsMonetaryAmount("$43.01");
        assertEquals(43.01, amount2.asDouble());
    }

    @Test
    @DisplayName("Parse decimal 99 money string")
    public void testParsDecimal99AsMonetaryAmountValidInput() throws ParseException {
        MonetaryAmount expectedMonetaryAmount = new MonetaryAmount(99.99);
        MonetaryAmount monetaryAmount = parseAsMonetaryAmount("$99.99");
        assertEquals(expectedMonetaryAmount, monetaryAmount);
    }

}
