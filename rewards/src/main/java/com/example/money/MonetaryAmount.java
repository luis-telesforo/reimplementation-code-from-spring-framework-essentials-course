package com.example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_EVEN;

/**
 * A Java Record representation of Money.
 * Immutable and value object.
 */
record MonetaryAmount(BigDecimal value) {

    /**
     * Create a new monetary amount from the specified value.
     * @param value the value of the amount; for example, in $USD "10.00" would be ten dollars, ".29" would be 29 cents
     */
    MonetaryAmount {
        value = value.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Create a new monetary amount from the specified value.
     * @param value the monetary amount as a double
     */
    MonetaryAmount(double value) {
        this(BigDecimal.valueOf(value));
    }

    /**
     * Convert the string representation of a monetary amount (e.g. $5 or 5) to a MonetaryAmount object.
     * @param string the monetary amount string
     * @return the monetary amount object
     */
    static MonetaryAmount fromString(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("The monetary amount value is required");
        }
        if (string.startsWith("$")) {
            int index = string.indexOf('$');
            string = string.substring(index + 1);
        }
        return new MonetaryAmount(new BigDecimal(string));
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
        return new MonetaryAmount(this.value.multiply(BigDecimal.valueOf(percentage)));
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

