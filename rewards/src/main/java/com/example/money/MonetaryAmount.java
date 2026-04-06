package com.example.money;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_EVEN;

/**
 * A Java Record representation of Money.
 */
public record MonetaryAmount(BigDecimal value) {

    /**
     * Create a new monetary amount from the specified value.
     * @param value the value of the amount; for example, in $USD "10.00" would be ten dollars, ".29" would be 29 cents
     */
    public MonetaryAmount {
        value = value.setScale(2, HALF_EVEN);
    }

    /**
     * Create a new monetary amount from the specified value.
     * @param value the monetary amount as a double
     */
    MonetaryAmount(double value) {
        this(valueOf(value));
    }

    /**
     * Returns the zero (0.00) monetary amount.
     */
    static MonetaryAmount zero() {
        return new MonetaryAmount(0);
    }

    /**
     * Add to this monetary amount, returning the sum as a new monetary amount.
     * @param amount the amount to add
     * @return the sum
     */
    MonetaryAmount plus(MonetaryAmount amount) {
        return new MonetaryAmount(this.value.add(amount.value));
    }

    /**
     * Subtract from this monetary amount, returning the difference as a new monetary amount.
     * @param amount the amount to subtract
     * @return the difference
     */
    MonetaryAmount minus(MonetaryAmount amount) {
        return new MonetaryAmount(this.value.subtract(amount.value));
    }

    /**
     * Multiply this monetary amount by a percentage.
     * @param percentage the percentage
     * @return the percentage amount
     */
    MonetaryAmount multiplyBy(double percentage) {
        return new MonetaryAmount(this.value.multiply(valueOf(percentage)));
    }

    /**
     * Returns true if this amount is greater than the amount.
     * @param amount the monetary amount
     * @return true or false
     */
    boolean greaterThan(MonetaryAmount amount) {
        return this.value.compareTo(amount.value) > 0;
    }

    /**
     * Get this amount as a double. Useful for when a double type is needed by an external API or system.
     * @return this amount as a double
     */
    double asDouble() {
        return this.value.doubleValue();
    }

    /**
     * Get this amount as a big decimal. Useful for when a BigDecimal type is needed by an external API or system.
     * @return this amount as a big decimal
     */
    BigDecimal asBigDecimal() {
        return this.value;
    }
}

