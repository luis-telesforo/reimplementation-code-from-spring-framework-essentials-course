package com.example.money;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.US;

/**
 * Formats {@link MonetaryAmount} as {@link String Strings} representing money values in USD.
 * It also parses {@link String Strings} representing money in USD to {@link MonetaryAmount}
 */
public final class MonetaryAmountFormatUtil {
    /**
     * Instance of {@link NumberFormat#getCurrencyInstance(Locale)} for {@link Locale#US}.
     */
    private static final NumberFormat CURRENCY_FORMATTER = getCurrencyInstance(US);

    /**
     * Private constructor prevents instantiation of the utility class.
     */
    private MonetaryAmountFormatUtil() {
    }

    /**
     * Formats a {@link MonetaryAmount} as a {@link String} in USD format.
     *
     * @param monetaryAmount The {@link MonetaryAmount} to format as a monetary value in USD.
     * @return A formatted string representing the monetary amount in USD.
     */
    public static String formatMonetaryAmount(MonetaryAmount monetaryAmount) {
        return CURRENCY_FORMATTER.format(monetaryAmount.asDouble());
    }

    /**
     * Parses a string representing a monetary value in USD and returns its
     * {@link MonetaryAmount} representation.
     *
     * @param amountString The string to parse as a monetary amount in USD.
     * @return The {@link MonetaryAmount} representing the parsed monetary amount in USD.
     * @throws ParseException if the parsing fails.
     */
    public static MonetaryAmount parseAsMonetaryAmount(String amountString) throws ParseException {
        Number number = CURRENCY_FORMATTER.parse(amountString);
        return new MonetaryAmount(number.doubleValue());
    }
}

