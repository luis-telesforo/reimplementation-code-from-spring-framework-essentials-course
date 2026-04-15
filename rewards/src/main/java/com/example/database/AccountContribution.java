package com.example.database;

import com.example.money.MonetaryAmount;

import java.util.Set;

/**
 * A summary of a monetary contribution made to an account that was distributed among the
 * account's beneficiaries.
 *
 * @param accountNumber the number of the account the contribution was made
 * @param amount the total contribution as {@link MonetaryAmount}
 * @param distributions how the contribution was distributed among the account's beneficiaries
 */
public record AccountContribution(
        String accountNumber, MonetaryAmount amount, Set<Distribution> distributions) {
    /**
     * For queries to {@link AccountContribution#distributions()} with an invalid beneficiary.
     */
    public static final String ERROR_MESSAGE_DISTRIBUTION_ACCESS = "No such distribution for: ";

    /**
     * Returns how this contribution was distributed to a single account beneficiary.
     *
     * @param beneficiary the name of the beneficiary e.g "Annabelle"
     * @return the {@link Distribution} associated to the beneficiary
     */
    public Distribution getDistribution(String beneficiary) {
        for (Distribution distribution: distributions) {
            if (distribution.beneficiary().equals(beneficiary)) {
                return distribution;
            }
        }
        throw new IllegalArgumentException(ERROR_MESSAGE_DISTRIBUTION_ACCESS + beneficiary);
    }
}
