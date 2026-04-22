package com.example.database;

import com.example.money.MonetaryAmount;
import com.example.money.Percentage;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static com.example.money.MonetaryAmount.zero;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * A single beneficiary allocated to an account. Each {@link Beneficiary} has a name,
 * an allocation {@link Percentage} and a savings balance tracking how much
 * money has been saved for them to date as {@link MonetaryAmount}.
 */
@Entity
@Table(name = "T_ACCOUNT_BENEFICIARY")
public class Beneficiary {

    @Id
    @Column(name = "ID",  nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long entityId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "ALLOCATION_PERCENTAGE", nullable = false))
    private Percentage allocationPercentage;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "SAVINGS", nullable = false))
    private MonetaryAmount savings = zero();

    /**
     * Necessary for {@link Entity}.
     */
    protected Beneficiary() {
    }

    /**
     * Creates a new account {@link Beneficiary} with {@link MonetaryAmount#zero()} savings.
     *
     * @param name the name of the beneficiary
     * @param allocationPercentage the beneficiary's allocation {@link Percentage} within its
     *                             account
     */
    public Beneficiary(String name, Percentage allocationPercentage) {
        this.name = name;
        this.allocationPercentage = allocationPercentage;
    }

    /**
     * Creates a new account beneficiary. This constructor should be called by
     * privileged objects responsible for reconstituting an existing {@link Account}
     * from some external form such as a collection of database records.
     * Marked package-private to indicate this constructor should never be
     * called by general application code.
     *
     * @param name the name of the beneficiary
     * @param allocationPercentage the beneficiary's allocation percentage within its account
     * @param savings the total amount saved to-date for this beneficiary
     */
    Beneficiary(String name, Percentage allocationPercentage,
                MonetaryAmount savings) {
        this.name = name;
        this.allocationPercentage = allocationPercentage;
        this.savings = savings;
    }

    /**
     * Returns the entity identifier used to internally distinguish this entity
     * among other entities of the same type in the system. Should typically
     * only be called by privileged data access infrastructure code such as an
     * Object Relational Mapper (ORM) and not by application code.
     *
     * @return the internal entity identifier
     */
    public Long getEntityId() {
        return entityId;
    }

    /**
     * Sets the internal entity identifier - should only be called by privileged
     * data access code (repositories that work with an Object Relational Mapper
     * (ORM)). Should never be set by application code explicitly.
     *
     * @param entityId the internal entity identifier
     */
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    /**
     * Returns the {@link Beneficiary} name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the beneficiary's allocation {@link Percentage} in this account.
     *
     * @return the {@link Percentage}
     */
    public Percentage getAllocationPercentage() {
        return allocationPercentage;
    }

    /**
     * Sets the beneficiary's allocation {@link Percentage} in this account.
     *
     * @param allocationPercentage The new allocation percentage
     */
    public void setAllocationPercentage(Percentage allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }

    /**
     * Returns the {@link MonetaryAmount} this beneficiary has accrued.
     *
     * @return the savings
     */
    public MonetaryAmount getSavings() {
        return savings;
    }

    /**
     * Credit the given {@link MonetaryAmount} to this beneficiary's saving balance.
     *
     * @param amount  the amount to credit
     */
    public void credit(MonetaryAmount amount) {
        savings = savings.plus(amount);
    }
}
