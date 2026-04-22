package com.example.rewards;

import com.example.database.AccountContribution;
import com.example.database.Distribution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.money.MonetaryAmount.zero;
import static com.example.money.Percentage.oneHundred;
import static java.util.Set.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class AccountContributionTests {
    @Test
    @DisplayName("The correct Distribution is returned")
    void testGetDistribution() {
        Distribution distributionA = new Distribution("a", zero(), oneHundred(), zero());
        Distribution distributionB = new Distribution("b", zero(), oneHundred(), zero());
        AccountContribution accountContribution = new AccountContribution("",
                zero(), of(distributionA, distributionB));
        Distribution actualDistribution = accountContribution.getDistribution("a");
        assertEquals(distributionA, actualDistribution);
    }

    @Test
    @DisplayName("Exception is thrown if looking for a non-existent Distribution")
    void testNegativeGetDistribution() {
        Distribution distributionA = new Distribution("a", zero(), oneHundred(), zero());
        AccountContribution accountContribution = new AccountContribution("", zero(),
                of(distributionA));
        IllegalArgumentException illegalArgumentException =
                assertThrowsExactly(IllegalArgumentException.class,
                        () -> accountContribution.getDistribution("b"));
        assertEquals(AccountContribution.ERROR_MESSAGE_DISTRIBUTION_ACCESS + "b",
                illegalArgumentException.getMessage());
    }
}
