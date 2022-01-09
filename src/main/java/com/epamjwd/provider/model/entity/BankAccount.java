package com.epamjwd.provider.model.entity;

import java.math.BigDecimal;

public class BankAccount implements Identifiable {
    private long bankAccountId;
    private BigDecimal balance;
    private long tariffId;

    public BankAccount() {
    }

    public BankAccount(long bankAccountId, BigDecimal balance, long tariffId) {
        this.bankAccountId = bankAccountId;
        this.balance = balance;
        this.tariffId = tariffId;
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

    public long getTariffId() {
        return tariffId;
    }

    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (bankAccountId != that.bankAccountId) return false;
        if (tariffId != that.tariffId) return false;
        return balance != null ? balance.equals(that.balance) : that.balance == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (bankAccountId ^ (bankAccountId >>> 32));
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (int) (tariffId ^ (tariffId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankAccount{");
        sb.append("bankAccountId=").append(bankAccountId);
        sb.append(", balance=").append(balance);
        sb.append(", tariffId=").append(tariffId);
        sb.append('}');
        return sb.toString();
    }
}
