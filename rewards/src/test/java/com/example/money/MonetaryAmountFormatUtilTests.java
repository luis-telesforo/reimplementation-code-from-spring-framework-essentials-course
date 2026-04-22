package com.example.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static com.example.money.MonetaryAmountFormatUtil.formatMonetaryAmount;
import static com.example.money.MonetaryAmountFormatUtil.parseAsMonetaryAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Tests for {@link MonetaryAmountFormatUtil}.
 */
@DisplayName("MonetaryAmountFormatUtil Tests")
class MonetaryAmountFormatUtilTests {

    @Test
    @DisplayName("Format no decimal MonetaryAmounts")
    void testFormatNoDecimalMonetaryAmounts() {
        MonetaryAmount amount1 = new MonetaryAmount(2);
        assertEquals("$2.00", formatMonetaryAmount(amount1));
    }

    @Test
    @DisplayName("Format decimal MonetaryAmounts")
    void testFormatDecimalMonetaryAmounts() {
        MonetaryAmount amount3 = new MonetaryAmount(10.01);
        assertEquals("$10.01", formatMonetaryAmount(amount3));
    }

    @Test
    @DisplayName("Format decimal MonetaryAmounts")
    void testFormatDecimalMonetaryAmount() {
        MonetaryAmount monetaryAmount = new MonetaryAmount(10.01);
        assertEquals("$10.01", formatMonetaryAmount(monetaryAmount));
    }

    @Test
    @DisplayName("Parse no decimal money strings")
    void testParseNoDecimalAsMonetaryAmount() throws ParseException {

        MonetaryAmount amount2 = parseAsMonetaryAmount("$43");
        assertEquals(43, amount2.asDouble());
    }

    @Test
    @DisplayName("Parse zero decimal money strings")
    void testParseZeroDecimalMonetaryAmountValidInput() throws ParseException {
        MonetaryAmount amount2 = parseAsMonetaryAmount("$43.00");
        assertEquals(43, amount2.asDouble());
    }

    @Test
    @DisplayName("Parse decimal money string")
    void testParseDecimalMonetaryAmountValidInput() throws ParseException {
        MonetaryAmount monetaryAmount = parseAsMonetaryAmount("$43.01");
        assertEquals(43.01, monetaryAmount.asDouble());
    }

    @Test
    @DisplayName("Parse decimal 99 money string")
    void testParseDecimal99AsMonetaryAmountValidInput() throws ParseException {
        MonetaryAmount expectedMonetaryAmount = new MonetaryAmount(99.99);
        MonetaryAmount monetaryAmount = parseAsMonetaryAmount("$99.99");
        assertEquals(expectedMonetaryAmount, monetaryAmount);
    }

    @Test
    @DisplayName("Parse decimal money string for rounding up")
    void testParseDecimalRoundingUp() throws ParseException {
        MonetaryAmount expectedMonetaryAmount = new MonetaryAmount(10.51);
        MonetaryAmount monetaryAmount = parseAsMonetaryAmount("$10.509");
        assertEquals(expectedMonetaryAmount, monetaryAmount);
    }

    @Test
    @DisplayName("Parse decimal money string for rounding down")
    void testParseDecimalRoundingDown() throws ParseException {
        MonetaryAmount expectedMonetaryAmount = new MonetaryAmount(10.5);
        MonetaryAmount monetaryAmount = parseAsMonetaryAmount("$10.501");
        assertEquals(expectedMonetaryAmount, monetaryAmount);
    }

    @Test
    @DisplayName("Not parsing money without dollar sign")
    void testParseDollarSignNegative() {
        assertThrowsExactly(ParseException.class, () -> parseAsMonetaryAmount("10.50"));
    }

    @Test
    @DisplayName("Not parsing money with USD instead of dollar sign")
    void testParseUsdNegative() {
        assertThrowsExactly(ParseException.class, () -> parseAsMonetaryAmount("10.50USD"));
    }

    @Test
    @DisplayName("Parse with USD and dollar sign")
    void testParseUsdPositive() throws ParseException {
        MonetaryAmount monetaryAmount = parseAsMonetaryAmount("$43.01USD");
        assertEquals(43.01, monetaryAmount.asDouble());
    }

    @Test
    @DisplayName("Formatting and Parsing do not alter MonetaryAmount")
    void testFormatAndParseConsistency() throws ParseException {
        MonetaryAmount original = new MonetaryAmount(10.25);
        String formatted = formatMonetaryAmount(original);
        MonetaryAmount parsed = parseAsMonetaryAmount(formatted);
        assertEquals(original, parsed, "Formatting and parsing should be consistent");
    }
}
