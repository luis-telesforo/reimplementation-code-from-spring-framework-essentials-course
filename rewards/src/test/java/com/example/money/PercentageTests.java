package com.example.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.example.money.Percentage.CONSTRUCTOR_ERROR_MESSAGE;
import static com.example.money.Percentage.oneHundred;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link Percentage}.
 */
@DisplayName("Percentage tests")
class PercentageTests {

    @Test
    @DisplayName("Constructor from double")
    void testPercentageConstructorFromDouble() {
        Percentage percentage = new Percentage(0.05);
        assertEquals(getBigDecimal(0.05), percentage.value());
    }

    @Test
    @DisplayName("Constructor failure if more than 1")
    void testPercentageConstructorExceedingOneHundred() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Percentage(2)
        );
        assertEquals(CONSTRUCTOR_ERROR_MESSAGE + "2.00", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor failure if less than 0")
    void testPercentageConstructorLessThanZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Percentage(-1)
        );
        assertEquals(CONSTRUCTOR_ERROR_MESSAGE + "-1.00", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor from BigDecimal")
    void testPercentageConstructorFromBigDecimal() {
        BigDecimal value = getBigDecimal(0.05);
        Percentage percentage = new Percentage(value);
        assertEquals(value, percentage.value());
    }

    @Test
    @DisplayName("Zero percentage")
    void testZeroPercentage() {
        Percentage zero = Percentage.zero();
        assertEquals(getBigDecimal(0), zero.value());
    }

    @Test
    @DisplayName("One hundred percentage")
    void testOneHundredPercentage() {
        Percentage oneHundred = oneHundred();
        assertEquals(getBigDecimal(1), oneHundred.value());
    }

    @Test
    @DisplayName("Add percentages")
    void testAddPercentages() {
        Percentage percentage1 = new Percentage(0.3);
        Percentage percentage2 = new Percentage(0.2);
        Percentage sum = percentage1.add(percentage2);
        assertEquals(getBigDecimal(0.5), sum.value());
    }

    @Test
    @DisplayName("Add percentages exceeds 100%")
    void testAddPercentagesExceeds100() {
        Percentage percentage1 = new Percentage(0.6);
        Percentage percentage2 = new Percentage(0.5);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> percentage1.add(percentage2)
        );
        assertEquals(CONSTRUCTOR_ERROR_MESSAGE + "1.10", exception.getMessage());
    }

    /**
     * Calls {@link BigDecimal#valueOf(double)} and then
     * {@link BigDecimal#setScale(int, RoundingMode)} to match
     * {@link Percentage} definition.
     *
     * @param value The value to be set as {@link BigDecimal}.
     * @return The {@link BigDecimal} as explained above.
     */
    private static BigDecimal getBigDecimal(double value) {
        return valueOf(value).setScale(2, HALF_UP);
    }
}
