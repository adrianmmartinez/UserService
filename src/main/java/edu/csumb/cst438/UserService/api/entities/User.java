package edu.csumb.cst438.UserService.api.entities;

public class User {
    private String id;
    private String username;
    private int balance;

    public User(String id, String username, int credit) {
        this.id = id;
        this.username = username;
        this.balance = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
