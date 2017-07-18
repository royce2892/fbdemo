package com.accubits.firebasedemo;

import java.util.HashMap;

/**
 * Created by Royce RB on 14/7/17.
 */

public class CustomObject {

    String email,what,who,how,app_open,logout,login;
    public CustomObject(HashMap<String,String> map ) {
        email = map.get("email");
        what = String.valueOf(map.get("what"));
        who = String.valueOf(map.get("who"));
        how = String.valueOf(map.get("how"));
        app_open = String.valueOf(map.get("app_open"));
        login = String.valueOf(map.get("login"));
        logout = String.valueOf(map.get("logout"));
       /* who = map.get("who")+"";
        how = map.get("how")+"";
        app_open = map.get("app_open");
        login = map.get("login")+"";
        logout = map.get("logout")+"";*/
    }

    public CustomObject() {
        email ="Email";
        login ="Login";
        logout = "Logout";
    }

    public String getEmail() {
        return email;
    }

    public String getWhat() {
        return what;
    }

    public String getWho() {
        return who;
    }

    public String getHow() {
        return how;
    }

    public String getApp_open() {
        return app_open;
    }

    public String getLogout() {
        return logout;
    }

    public String getLogin() {
        return login;
    }
}
