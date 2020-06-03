package com.javacourse.view;

import java.util.ResourceBundle;

public class PagePath {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("page_location");

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
