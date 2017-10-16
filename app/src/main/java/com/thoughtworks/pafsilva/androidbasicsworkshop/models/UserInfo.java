package com.thoughtworks.pafsilva.androidbasicsworkshop.models;

public class UserInfo {
    private String firstName;
    private String lastName;
    private String category;
    private String points;

    public UserInfo(String firstName, String lastName, String category, String points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.points = points;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCategory() {
        return category;
    }

    public String getPoints() {
        return points;
    }
}
