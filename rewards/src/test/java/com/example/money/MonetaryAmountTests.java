package com.example.money;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.example.money.MonetaryAmount.zero;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonetaryAmountTests {

    @Test
    void testMonetaryAmountConstructor() {
        MonetaryAmount amount = new MonetaryAmount(10.5);
        assertEquals(getBigDecimal(10.5), amount.value());
    }

    @Test
    void testMonetaryAmountValueOf() {
        MonetaryAmount amount = MonetaryAmount.fromString("$10.50");
        assertEquals(getBigDecimal(10.5), amount.value());
    }

    @Test
    void testMonetaryAmountZero() {
        MonetaryAmount zero = zero();
        assertEquals(getBigDecimal(0), zero.value());
    }

    @Test
    void testMonetaryAmountPlus() {
        MonetaryAmount amount1 = new MonetaryAmount(5);
        MonetaryAmount amount2 = new MonetaryAmount(3);
        MonetaryAmount sum = amount1.plus(amount2);
        assertEquals(getBigDecimal(8), sum.value());
    }

    @Test
    void testMonetaryAmountMinus() {
        MonetaryAmount amount1 = new MonetaryAmount(5);
        MonetaryAmount amount2 = new MonetaryAmount(3);
        MonetaryAmount difference = amount1.minus(amount2);
        assertEquals(getBigDecimal(2), difference.value());
    }

    @Test
    void testMonetaryAmountMultiplyBy() {
        MonetaryAmount amount = new MonetaryAmount(5);
        MonetaryAmount result = amount.multiplyBy(2);
        assertEquals(getBigDecimal(10), result.value());
    }

    @Test
    void testMonetaryAmountGreaterThan() {
        MonetaryAmount amount1 = new MonetaryAmount(5);
        MonetaryAmount amount2 = new MonetaryAmount(3);
        assertTrue(amount1.greaterThan(amount2));
    }

    @Test
    void testMonetaryAmountAsDouble() {
        MonetaryAmount amount = new MonetaryAmount(5.5);
        assertEquals(5.5, amount.asDouble(), 0.001);
    }

    @Test
    void testMonetaryAmountAsBigDecimal() {
        MonetaryAmount amount = new MonetaryAmount(new BigDecimal("5.5"));
        assertEquals(getBigDecimal(5.5), amount.asBigDecimal());
    }

    private static @NonNull BigDecimal getBigDecimal(double val) {
        return BigDecimal.valueOf(val).setScale(2, HALF_EVEN);
    }
}

