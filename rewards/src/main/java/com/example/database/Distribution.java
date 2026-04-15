package com.example.database;

import com.example.money.MonetaryAmount;
import com.example.money.Percentage;

import java.io.Serializable;

/**
 * A single distribution made to a beneficiary as part of an account contribution, summarizing
 * the distribution
 * amount and resulting total beneficiary savings.
 *
 * @param beneficiary  the name of the account beneficiary that received a distribution
 * @param amount       the distribution amount
 * @param percentage   this distribution's percentage of the total account contribution
 * @param totalSavings the beneficiary's total savings amount after the distribution was made
 */
public record Distribution(String beneficiary, MonetaryAmount amount, Percentage percentage,
                           MonetaryAmount totalSavings) implements Serializable {

}
