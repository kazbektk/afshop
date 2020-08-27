package com.epam.afshop.regex;

public final class RegularExpressionConstant {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PHONE_REGEX = "^\\+([0-9\\-]?){9,11}[0-9]$";
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9]{6,}$";

    public static String getPasswordRegex() {
        return PASSWORD_REGEX;
    }

    public static String getPhoneRegex() {
        return PHONE_REGEX;
    }

    public static String getEmailRegex() {
        return EMAIL_REGEX;
    }

    public RegularExpressionConstant() {
    }
}
