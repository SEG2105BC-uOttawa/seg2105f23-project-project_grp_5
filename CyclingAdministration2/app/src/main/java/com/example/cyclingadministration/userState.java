package com.example.cyclingadministration;

import android.app.Application;

public class userState extends Application {

    private boolean userState = false;

    public boolean getUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }
}

