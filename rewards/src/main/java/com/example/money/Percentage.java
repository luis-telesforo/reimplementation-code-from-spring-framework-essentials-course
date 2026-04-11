package com.example.money;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

/**
 * A Java Record representation of a percentage.
 *
 * @param value the percentage value, where 1.0 represents 100%
 */
public record Percentage(BigDecimal value) {
    /**
     * Error message for bad argument used in constructor.
     */
    public static final String CONSTRUCTOR_ERROR_MESSAGE =
            "Percentage value must be between 0 and 1; your value was ";

    /**
     * Create a new percentage from the specified value.
     *
     * @param value the percentage value; for example, 0.05 would represent 5%
     */
    public Percentage {
        value = value.setScale(2, HALF_UP);
        if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException(
                    CONSTRUCTOR_ERROR_MESSAGE + value);
        }
    }

    /**
     * Create a new {@link Percentage} from the specified value.
     *
     * @param value the percentage as a {@code double}
     */
    public Percentage(double value) {
        this(valueOf(value));
    }

    /**
     * Returns zero percentage.
     *
     * @return the zero percentage
     */
    public static Percentage zero() {
        return new Percentage(0);
    }

    /**
     * Returns one hundred percentage.
     *
     * @return the one hundred percent percentage
     */
    public static Percentage oneHundred() {
        return new Percentage(1);
    }

    /**
     * Add to this percentage.
     *
     * @param percentage the {@link Percentage} to add
     * @return the sum
     * @throws IllegalArgumentException if the sum exceeds 1
     */
    public Percentage add(Percentage percentage) throws IllegalArgumentException {
        BigDecimal sum = this.value.add(percentage.value);
        return new Percentage(sum);
    }

    /**
     * Return this percentage as a double.
     *
     * @return this percentage as a double
     */
    public double asDouble() {
        return value.doubleValue();
    }
}
