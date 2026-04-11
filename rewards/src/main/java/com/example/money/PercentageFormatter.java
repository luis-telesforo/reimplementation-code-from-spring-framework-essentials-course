package com.example.money;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static java.text.NumberFormat.getPercentInstance;
import static java.util.Locale.US;

/**
 * Formats {@link Percentage} as {@link String}s representing percentages.
 * It also parses {@link String Strings} to {@link Percentage}.
 */
public final class PercentageFormatter {
    /**
     * Instance of {@link NumberFormat#getPercentInstance(Locale)} for {@link Locale#US}.
     */
    private static final NumberFormat PERCENT_FORMATTER = getPercentInstance(US);

    /**
     * Private constructor prevents instantiation of the utility class.
     */
    private PercentageFormatter() {
    }

    /**
     * Formats a {@link Percentage} as a {@link String} in US format.
     *
     * @param percentage The {@link Percentage} to format.
     * @return A formatted string representing teh percentage.
     */
    public static String formatPercentage(Percentage percentage) {
        return PERCENT_FORMATTER.format(percentage.asDouble());
    }

    /**
     * Parses a string representing a percentage string and returns its
     * {@link Percentage} representation.
     *
     * @param percentageString The string to parse as a {@link Percentage}.
     * @return The {@link Percentage} representing the parsed {@link String}.
     * @throws ParseException if the parsing fails.
     */
    public static Percentage parseToPercentage(String percentageString) throws ParseException {
        Number number = PERCENT_FORMATTER.parse(percentageString);
        return new Percentage(number.doubleValue());
    }
}
