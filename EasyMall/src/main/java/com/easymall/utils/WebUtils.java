package com.easymall.utils;

public final class WebUtils {
    private WebUtils() {
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }
}