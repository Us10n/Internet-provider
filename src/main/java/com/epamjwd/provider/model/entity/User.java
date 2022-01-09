package com.epamjwd.provider.model.entity;

public class User implements Identifiable {
    private long userId;
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private long roleId;
    private long statusId;
    private long bankAccountId;

    public User() {
    }

    public User(long id, String login, String password, String email, String name, String surname, long roleId, long statusId, long bankAccountId) {
        this.userId = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
        this.statusId = statusId;
        this.bankAccountId = bankAccountId;
    }

    @Override
    public long getId() {
        return userId;
    }

    @Override
    public void setId(long id) {
        userId = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (roleId != user.roleId) return false;
        if (statusId != user.statusId) return false;
        if (bankAccountId != user.bankAccountId) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return surname != null ? surname.equals(user.surname) : user.surname == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (int) (statusId ^ (statusId >>> 32));
        result = 31 * result + (int) (bankAccountId ^ (bankAccountId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", roleId=").append(roleId);
        sb.append(", statusId=").append(statusId);
        sb.append(", bankAccountId=").append(bankAccountId);
        sb.append('}');
        return sb.toString();
    }
}
