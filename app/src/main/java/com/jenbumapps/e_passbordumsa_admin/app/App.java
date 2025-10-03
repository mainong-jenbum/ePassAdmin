package com.jenbumapps.e_passbordumsa_admin.app;

import android.app.Application;

import com.jenbumapps.core.model.User;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    public static Map<String, String> globalParams = new HashMap<>();
    public static User user;
}
