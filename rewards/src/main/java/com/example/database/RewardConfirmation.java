package com.example.database;

/**
 * A summary of a confirmed reward transaction describing a contribution made to an account that was distributed among
 * the account's beneficiaries.
 *
 * @param confirmationNumber the unique confirmation number
 * @param accountContribution the {@link AccountContribution} that was made
 */
public record RewardConfirmation(String confirmationNumber, AccountContribution accountContribution) {
}
