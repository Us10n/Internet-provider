package com.epamjwd.provider.model.entity;

import java.math.BigDecimal;
import java.util.Optional;

public class BankAccount implements Identifiable {
    private long bankAccountId;
    private BigDecimal balance;
    private long userId;
    private Optional<Long> tariffId;

    public BankAccount() {
    }

    public BankAccount(long bankAccountId, BigDecimal balance,
                       long userId, Long tariffId) {
        this.bankAccountId = bankAccountId;
        this.balance = balance;
        this.userId = userId;
        this.tariffId = Optional.ofNullable(tariffId);
    }

    @Override
    public long getId() {
        return bankAccountId;
    }

    @Override
    public void setId(long id) {
        this.bankAccountId = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Optional<Long> getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = Optional.ofNullable(tariffId);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (bankAccountId != that.bankAccountId) return false;
        if (userId != that.userId) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        return tariffId.isPresent() ? tariffId.equals(that.tariffId) : that.tariffId.isEmpty();
    }

    @Override
    public int hashCode() {
        int result = (int) (bankAccountId ^ (bankAccountId >>> 32));
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (tariffId.isPresent() ? tariffId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankAccount{");
        sb.append("bankAccountId=").append(bankAccountId);
        sb.append(", balance=").append(balance);
        sb.append(", userId=").append(userId);
        sb.append(", tariffId=").append(tariffId);
        sb.append('}');
        return sb.toString();
    }
}
