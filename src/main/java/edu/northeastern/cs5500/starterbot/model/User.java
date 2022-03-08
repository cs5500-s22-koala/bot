package edu.northeastern.cs5500.starterbot.model;

public class User {
    int id;
    String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    private int getId() {
        return this.id;
    }

    private String getUsername() {
        return this.username;
    }

    private void setId(int newId) {
        this.id = newId;
    }

    private void setUsername(String newUsername) {
        this.username = newUsername;
    }
}
