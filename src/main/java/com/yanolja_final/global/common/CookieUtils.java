package com.yanolja_final.global.common;

import jakarta.servlet.http.Cookie;

public class CookieUtils {

    private CookieUtils() {
    }

    public static Cookie makeCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setAttribute("Samesite", "None");

        cookie.setDomain("localhost");
        cookie.setSecure(false);

        return cookie;
    }
}
