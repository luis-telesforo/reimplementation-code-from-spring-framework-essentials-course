package com.example.database;

import com.example.money.MonetaryAmount;

import java.text.ParseException;
import java.time.LocalDate;

import static com.example.datetime.LocalDateFormatter.today;
import static com.example.money.MonetaryAmountFormatter.parseAsMonetaryAmount;
import static java.time.LocalDate.of;

/**
 * Represents a dining event, including the amount charged, credit card used,
 * merchant number, and date.
 *
 * @param amount the {@link MonetaryAmount} of the dining bill as a string
 * @param creditCardNumber the number of the credit card used to pay for the dining bill
 * @param merchantNumber the merchant number of the restaurant where the dining occurred
 * @param date the date the dining event happened
 */
public record Dining(
        MonetaryAmount amount, String creditCardNumber, String merchantNumber, LocalDate date) {

    /**
     * Creates a new dining, reflecting an amount that was charged to a credit card by
     * a merchant on today's date.
     *
     * @param amount the total amount of the dining bill as a string
     * @param creditCardNumber the number of the credit card used to pay for the dining bill
     * @param merchantNumber the merchant number of the restaurant where the dining occurred
     * @return the dining event
     */
    public static Dining createDining(
            String amount, String creditCardNumber, String merchantNumber) throws ParseException {
        return new Dining(parseAsMonetaryAmount(amount), creditCardNumber, merchantNumber,
                today());
    }

    /**
     * Creates a new dining, reflecting an amount that was charged to a credit card by
     * a merchant on the date specified.
     *
     * @param amount the total amount of the dining bill as a string
     * @param creditCardNumber the number of the credit card used to pay for the dining bill
     * @param merchantNumber the merchant number of the restaurant where the dining occurred
     * @param month the month of the dining event
     * @param day the day of the dining event
     * @param year the year of the dining event
     * @return the dining event
     */
    public static Dining createDining(String amount,
                                      String creditCardNumber,
                                      String merchantNumber,
                                      int month,
                                      int day,
                                      int year) throws ParseException {
        return new Dining(parseAsMonetaryAmount(amount),
                creditCardNumber,
                merchantNumber,
                of(year, month, day));
    }
}

