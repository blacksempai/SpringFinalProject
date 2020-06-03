package com.javacourse.view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    public static String getProperty(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies().clone();
        String lang = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("lang"))
                lang = c.getValue();
        }
        Locale locale = new Locale(lang != null ? lang : "ru");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);
        return resourceBundle.getString(key);
    }
}
