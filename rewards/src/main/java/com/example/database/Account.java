package com.example.database;

import com.example.money.MonetaryAmount;
import com.example.money.Percentage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.example.money.Percentage.oneHundred;
import static com.example.money.Percentage.zero;
import static jakarta.persistence.CascadeType.ALL;
import static java.util.Collections.unmodifiableSet;

/**
 * An account for a member of the reward network. An account has one or more
 * {@link Beneficiary Beneficiaries} whose allocations must add up to 100%.
 * An account can make contributions to its beneficiaries. Each contribution is
 * distributed among the beneficiaries based on an allocation.
 */
@Entity
@Table(name = "T_ACCOUNT")
public class Account {

    @Id
    @Column(name = "ID",  unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entityId;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "ACCOUNT_ID",  nullable = false)
    private final Set<Beneficiary> beneficiaries = new HashSet<>();

    protected Account() {
    }

    /**
     * Create a new account.
     *
     * @param number the account number
     * @param name the name on the account
     */
    public Account(String number, String name) {
        this.number = number;
        this.name = name;
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
     * Returns the number used to uniquely identify this account.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number used to uniquely identify this account.
     *
     * @param number the number for this account
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Returns the name on file for this account.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name on file for this account.
     *
     * @param name the name for this account
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add a single {@link Beneficiary} with a 100% allocation percentage.
     *
     * @param beneficiaryName the name of the {@link Beneficiary}
     */
    public void addBeneficiary(String beneficiaryName) {
        addBeneficiary(beneficiaryName, oneHundred());
    }

    /**
     * Add a single {@link Beneficiary} with the specified allocation {@link Percentage}.
     *
     * @param beneficiaryName the name of the {@link Beneficiary}
     * @param allocationPercentage the beneficiary's allocation {@link Percentage} within this
     *                             account
     */
    public void addBeneficiary(String beneficiaryName,
                               Percentage allocationPercentage) {
        beneficiaries
                .add(new Beneficiary(beneficiaryName, allocationPercentage));
    }

    /**
     * Returns the beneficiaries for this account.
     * <p>
     * Callers should not attempt to hold on or modify the returned set. This
     * method should only be used transitively; for example, called to
     * facilitate account reporting.
     *
     * @return the beneficiaries of this account
     */
    public Set<Beneficiary> getBeneficiaries() {
        return unmodifiableSet(beneficiaries);
    }

    /**
     * Returns a single account {@link Beneficiary}. Callers should not attempt to hold
     * on or modify the returned object. This method should only be used
     * transitively; for example, called to facilitate reporting or testing.
     *
     * @param name the name of the {@link Beneficiary}
     * @return the {@link Beneficiary}
     */
    public Beneficiary getBeneficiary(String name) {
        for (Beneficiary beneficiary : beneficiaries) {
            if (beneficiary.getName().equals(name)) {
                return beneficiary;
            }
        }
        throw new IllegalArgumentException("No such beneficiary with name '"
                + name + "'");
    }

    /**
     * Removes a single {@link Beneficiary} from this account.
     *
     * @param beneficiaryName the name of the {@link Beneficiary}
     */
    public void removeBeneficiary(String beneficiaryName) {
        beneficiaries.remove(getBeneficiary(beneficiaryName));
    }

    /**
     * Validation check that returns {@code true} only if the total beneficiary
     * allocation adds up to 100%.
     */
    public boolean isValid() {
        Percentage totalPercentage = zero();
        for (Beneficiary beneficiary : beneficiaries) {
            try {
                totalPercentage = totalPercentage.add(beneficiary
                        .getAllocationPercentage());
            } catch (IllegalArgumentException illegalArgumentException) {
                return false;
            }
        }
        return totalPercentage.equals(oneHundred());
    }

    public void setValid(boolean valid) {
        // DO NOTHING. Needed for JSON processing on client.
    }

    /**
     * Make a monetary contribution to this account. The contribution amount is
     * distributed among the account's beneficiaries based on each beneficiary's
     * allocation percentage.
     *
     * @param amount the total {@link MonetaryAmount} to contribute
     */
    public AccountContribution makeContribution(MonetaryAmount amount) {
        if (!isValid()) {
            throw new IllegalStateException(
                    "Cannot make contributions to this account: it has invalid beneficiary allocations");
        }
        Set<Distribution> distributions = distribute(amount);
        return new AccountContribution(getNumber(), amount, distributions);
    }

    /**
     * Distribute the contribution amount among this account's beneficiaries.
     *
     * @param amount the total contribution amount
     * @return the individual beneficiary distributions
     */
    private Set<Distribution> distribute(MonetaryAmount amount) {
        Set<Distribution> distributions = new HashSet<>(
                beneficiaries.size());
        for (Beneficiary beneficiary : beneficiaries) {
            MonetaryAmount distributionAmount = amount.multiplyBy(beneficiary
                    .getAllocationPercentage().asDouble());
            beneficiary.credit(distributionAmount);
            Distribution distribution = new Distribution(beneficiary.getName(),
                    distributionAmount, beneficiary.getAllocationPercentage(),
                    beneficiary.getSavings());
            distributions.add(distribution);
        }
        return distributions;
    }

    /**
     * Used to restore an allocated {@link  Beneficiary}. Should only be called by the
     * repository responsible for reconstituting this account.
     *
     * @param beneficiary the {@link  Beneficiary}
     */
    void restoreBeneficiary(Beneficiary beneficiary) {
        beneficiaries.add(beneficiary);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Account account = (Account) other;
        return Objects.equals(entityId, account.entityId) &&
                Objects.equals(number, account.number) &&
                Objects.equals(name, account.name) &&
                Objects.equals(beneficiaries, account.beneficiaries);
    }

    @Override
    public int hashCode() {

        return Objects.hash(entityId, number, name, beneficiaries);
    }
}