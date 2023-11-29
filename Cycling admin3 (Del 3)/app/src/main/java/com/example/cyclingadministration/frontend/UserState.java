package com.example.cyclingadministration.frontend;

import android.app.Application;

public class UserState extends Application {

    private boolean userState = false;
    private boolean isComplete = false;

    private String role, username, password;

    public boolean getUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }

    public boolean getIsCompleteForUser() {
        return isComplete;
    }

    public void setIsCompleteForUser(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getRoleForUser() {
        return role;
    }

    public void setRoleForUser(String role) {
        this.role = role;
    }

    public String getUsernameForUser() {
        return username;
    }

    public void setUsernameForUser(String username) {
        this.username = username;
    }

    public String getPasswordForUser() {
        return password;
    }

    public void setPasswordForUser(String password) {
        this.password = password;
    }

    public void reset() {
        this.userState = false;
        this.isComplete = false;

        this.role = "";
        this.username = "";
        this.password = "";

    }
}

