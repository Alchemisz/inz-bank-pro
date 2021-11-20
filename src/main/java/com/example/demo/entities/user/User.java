package com.example.demo.entities.user;

import com.example.demo.security.priviledges.UserPriviledges;

public class User {
    private String login;
    private String pass;
    private UserPriviledges userPriviledges;

    public User(String login, String pass, UserPriviledges userPriviledges) {
        this.login = login;
        this.pass = pass;
        this.userPriviledges = userPriviledges;
    }

    public UserPriviledges getUserPriviledges() {
        return userPriviledges;
    }

    public void setUserPriviledges(UserPriviledges userPriviledges) {
        this.userPriviledges = userPriviledges;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
