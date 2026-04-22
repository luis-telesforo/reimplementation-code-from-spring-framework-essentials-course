package com.example.money;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.example.money.MonetaryAmount.zero;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link MonetaryAmount}.
 */
@DisplayName("MonetaryAmount tests")
class MonetaryAmountTests {

    @Test
    @DisplayName("Non-Canonical constructor")
    void testMonetaryAmountConstructor() {
        MonetaryAmount monetaryAmount = new MonetaryAmount(10.5);
        assertEquals(getBigDecimal(10.5), monetaryAmount.value());
    }

    @Test
    @DisplayName("Non-Canonical constructor rounding up")
    void testMonetaryAmountConstructorRoundingUp() {
        MonetaryAmount monetaryAmount = new MonetaryAmount(10.5099999);
        assertEquals(getBigDecimal(10.51), monetaryAmount.value());
    }

    @Test
    @DisplayName("Non-Canonical constructor rounding down")
    void testMonetaryAmountConstructorRoundingDown() {
        MonetaryAmount monetaryAmount = new MonetaryAmount(10.50111111);
        assertEquals(getBigDecimal(10.5), monetaryAmount.value());
    }

    @Test
    @DisplayName("Zero MonetaryAmount")
    void testMonetaryAmountZero() {
        MonetaryAmount zero = zero();
        assertEquals(getBigDecimal(0), zero.value());
    }

    @Test
    @DisplayName("Addition of MonetaryAmounts")
    void testMonetaryAmountPlus() {
        MonetaryAmount monetaryAmount2 = new MonetaryAmount(5);
        MonetaryAmount monetaryAmount1 = new MonetaryAmount(3);
        MonetaryAmount sum = monetaryAmount2.plus(monetaryAmount1);
        assertEquals(getBigDecimal(8), sum.value());
    }

    @Test
    @DisplayName("Difference of MonetaryAmounts")
    void testMonetaryAmountMinus() {
        MonetaryAmount monetaryAmount2 = new MonetaryAmount(5);
        MonetaryAmount monetaryAmount1 = new MonetaryAmount(3);
        MonetaryAmount difference = monetaryAmount2.minus(monetaryAmount1);
        assertEquals(getBigDecimal(2), difference.value());
    }

    @Test
    @DisplayName("Product by double")
    void testMonetaryAmountMultiplyBy() {
        MonetaryAmount monetaryAmount = new MonetaryAmount(5);
        MonetaryAmount result = monetaryAmount.multiplyBy(2);
        assertEquals(getBigDecimal(10), result.value());
    }

    @Test
    @DisplayName("Comparison")
    void testMonetaryAmountGreaterThan() {
        MonetaryAmount monetaryAmount2 = new MonetaryAmount(5);
        MonetaryAmount monetaryAmount1 = new MonetaryAmount(3);
        assertTrue(monetaryAmount2.greaterThan(monetaryAmount1));
        assertFalse(monetaryAmount1.greaterThan(monetaryAmount2));
        assertFalse(monetaryAmount2.greaterThan(monetaryAmount2));
    }

    @Test
    @DisplayName("As double rounds value")
    void testMonetaryAmountCanonicalConstructor() {
        MonetaryAmount monetaryAmount = new MonetaryAmount(new BigDecimal("5.09999999999999"));
        assertEquals(5.1, monetaryAmount.asDouble());
    }

    /**
     * Calls {@link BigDecimal#valueOf(double)} and then
     * {@link BigDecimal#setScale(int, RoundingMode)} to match
     * {@link MonetaryAmount} definition.
     *
     * @param value The value to be set as {@link BigDecimal}.
     * @return The {@link BigDecimal} as explained above.
     */
    private static @NonNull BigDecimal getBigDecimal(double value) {
        return valueOf(value).setScale(2, HALF_EVEN);
    }
}

