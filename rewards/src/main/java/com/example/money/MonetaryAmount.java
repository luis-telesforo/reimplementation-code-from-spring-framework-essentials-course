package com.example.money;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_EVEN;

/**
 * A Java Record representation of Money.
 *
 * @param value the monetary value.
 */
public record MonetaryAmount(BigDecimal value) {

    /**
     * Create a new monetary amount from the specified value.
     *
     * @param value the value of the amount; for example, in $USD "10.00" would be ten dollars,
     *             ".29" would be 29 cents
     */
    public MonetaryAmount {
        value = value.setScale(2, HALF_EVEN);
    }

    /**
     * Create a new {@link MonetaryAmount} from the specified value.
     *
     * @param value the monetary amount as a {@code double}
     */
    MonetaryAmount(double value) {
        this(valueOf(value));
    }

    /**
     * Returns the zero (0.00) {@link MonetaryAmount}.
     */
    public static MonetaryAmount zero() {
        return new MonetaryAmount(0);
    }

    /**
     * Add to this {@link MonetaryAmount}, returning the sum as a new {@link MonetaryAmount}.
     *
     * @param amount the amount to add.
     * @return the sum.
     */
    public MonetaryAmount plus(MonetaryAmount amount) {
        return new MonetaryAmount(this.value.add(amount.value));
    }

    /**
     * Subtract from this {@link MonetaryAmount},
     * returning the difference as a new {@link MonetaryAmount}.
     *
     * @param amount the amount to subtract.
     * @return the difference.
     */
    MonetaryAmount minus(MonetaryAmount amount) {
        return new MonetaryAmount(this.value.subtract(amount.value));
    }

    /**
     * Multiply this {@link MonetaryAmount} by another amount (as {@code double}).
     *
     * @param amount the amount to multiply by.
     * @return the new amount.
     */
    MonetaryAmount multiplyBy(double amount) {
        return new MonetaryAmount(this.value.multiply(valueOf(amount)));
    }

    /**
     * Returns {@code true} if this amount is greater than the other {@link MonetaryAmount}.
     *
     * @param amount the monetary amount.
     * @return {@code true} if and only if this amount is greater than the other {@link MonetaryAmount}.
     */
    boolean greaterThan(MonetaryAmount amount) {
        return this.value.compareTo(amount.value) > 0;
    }

    /**
     * Get this amount as a {@code double}.
     * Useful for when a double type is needed by an external API or system.
     *
     * @return this amount as a {@code double}.
     */
    double asDouble() {
        return this.value.doubleValue();
    }
}

