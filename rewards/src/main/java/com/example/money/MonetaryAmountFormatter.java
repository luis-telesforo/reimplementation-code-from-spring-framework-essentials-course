package com.example.money;

import java.text.NumberFormat;
import java.text.ParseException;

import static com.example.money.MonetaryAmount.fromString;
import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.US;

/**
 * Utility class providing utility methods to manipulate monetary amounts in USD format.
 */
public final class MonetaryAmountFormatter {

    private static final NumberFormat CURRENCY_FORMATTER = getCurrencyInstance(US);

    /**
     * Private constructor prevents instantiation of the utility class.
     */
    private MonetaryAmountFormatter() {}

    /**
     * Formats a number as a monetary amount in USD.
     *
     * @param amount The amount to format as a monetary value in USD.
     * @return A formatted string representing the monetary amount in USD.
     */
    public static String formatToCurrency(double amount) {
        return CURRENCY_FORMATTER.format(amount);
    }

    /**
     * Parses a string representing a monetary amount in USD and returns the numeric value.
     *
     * @param amountString The string to parse as a monetary amount in USD.
     * @return The numeric value of the parsed monetary amount in USD, or null if parsing fails.
     */
    public static MonetaryAmount parse(String amountString) throws ParseException {
        Number number = CURRENCY_FORMATTER.parse(amountString);
        return fromString(number.toString());
    }
}

