package com.test.myapplication.db;

/**
 * Created by Alon on 06/01/2016.
 */
public class User {

    private String photo_url;
    private String name;
    private String title;
    private String email;

    public User() {
    }

    public User(String photo_url, String name, String title, String email) {
        this.photo_url = photo_url;
        this.name = name;
        this.title = title;
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
