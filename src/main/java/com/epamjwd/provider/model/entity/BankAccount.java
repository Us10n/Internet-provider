package com.epamjwd.provider.model.entity;

import java.math.BigDecimal;

public class BankAccount implements Identifiable {
    private long bankAccountId;
    private BigDecimal balance;
    private Tariff tariff;

    public BankAccount() {
    }

    public BankAccount(long bankAccountId, BigDecimal balance, Tariff tariff) {
        this.bankAccountId = bankAccountId;
        this.balance = balance;
        this.tariff = tariff;
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

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (bankAccountId != that.bankAccountId) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        return tariff != null ? tariff.equals(that.tariff) : that.tariff == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (bankAccountId ^ (bankAccountId >>> 32));
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (tariff != null ? tariff.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankAccount{");
        sb.append("bankAccountId=").append(bankAccountId);
        sb.append(", balance=").append(balance);
        sb.append(", tariff=").append(tariff);
        sb.append('}');
        return sb.toString();
    }
}
