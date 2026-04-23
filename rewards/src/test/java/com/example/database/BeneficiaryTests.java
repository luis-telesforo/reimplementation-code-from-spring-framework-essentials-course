package com.example.database;

import com.example.money.MonetaryAmount;
import com.example.money.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.money.MonetaryAmount.zero;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Tests for {@link Beneficiary}.
 */
class BeneficiaryTests {
    @Test
    @DisplayName("Constructor")
    void constructorTest() {
        String expectedName = "Test Beneficiary";
        Percentage expectedPercentage = new Percentage(.5);
        Beneficiary beneficiary = new Beneficiary(expectedName, expectedPercentage);
        assertNotNull(beneficiary);
        assertEquals(expectedName, beneficiary.getName());
        assertSame(expectedPercentage, beneficiary.getAllocationPercentage());
        assertNotNull(beneficiary.getSavings());
        assertEquals(beneficiary.getSavings(), zero());
    }

    @Test
    @DisplayName("Credit adds percentages to savings")
    void creditTest() {
        String expectedName = "Test Beneficiary";
        Percentage expectedPercentage = new Percentage(.5);
        Beneficiary beneficiary = new Beneficiary(expectedName, expectedPercentage);
        beneficiary.credit(new MonetaryAmount(6.0));
        beneficiary.credit(new MonetaryAmount(8.0));
        assertEquals(new MonetaryAmount(14.0), beneficiary.getSavings());
    }
}
