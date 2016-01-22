package com.test.myapplication.db;

/**
 * Created by Alon on 06/01/2016.
 */
public class UserContainer {
    User user;

    public UserContainer() {
    }

    public UserContainer(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
