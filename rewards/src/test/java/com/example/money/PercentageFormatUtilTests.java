package com.example.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static com.example.money.PercentageFormatUtil.formatPercentage;
import static com.example.money.PercentageFormatUtil.parseToPercentage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link PercentageFormatUtil}.
 */
@DisplayName("Tests for PercentageFormatUtil")
class PercentageFormatUtilTests {

    @Test
    @DisplayName("Formatting percentages")
    void testFormatPercentage() {
        assertEquals("12%",
                formatPercentage(new Percentage(0.1234)));
    }

    @Test
    @DisplayName("Parsing percentages")
    void testParseToPercentage() throws ParseException {
        assertEquals(new Percentage(0.1275), parseToPercentage("12.75%"));
    }

    @Test
    @DisplayName("The percent symbol should be at the end")
    void testParseToPercentageWithInvalidInput() {
        ParseException exception = assertThrows(ParseException.class,
                () -> parseToPercentage("%14"));
        String expectedMessage = "Unparseable number: \"%14\"";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("The percent symbol should be present")
    void testParseToPercentageWithNoPercentSymbol() {
        ParseException exception = assertThrows(ParseException.class,
                () -> parseToPercentage("14"));
        String expectedMessage = "Unparseable number: \"14\"";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Formatting and Parsing do not alter Percentage")
    void testFormatAndParseConsistency() throws ParseException {
        Percentage original = new Percentage(0.25);
        String formatted = formatPercentage(original);
        Percentage parsed = parseToPercentage(formatted);
        assertEquals(original, parsed, "Formatting and parsing should be consistent");
    }
}

