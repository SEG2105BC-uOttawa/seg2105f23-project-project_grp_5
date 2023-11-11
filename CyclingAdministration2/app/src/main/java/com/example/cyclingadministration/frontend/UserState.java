package com.example.cyclingadministration.frontend;

import android.app.Application;

public class UserState extends Application {

    private boolean userState = false;

    public boolean getUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }
}

