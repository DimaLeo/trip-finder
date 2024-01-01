package com.nik.tripfinder.util;

import java.util.regex.Pattern;

public class Validator {

    private static String passwordPattern = "^[a-zA-Z0-9!@#$%^&*()-_=+\\[\\]{}|;:'\",.<>?/`~]*$";
    private static String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static String taxCodePattern = "^[0-9]{9}$";
    private static String namePattern = "^[a-zA-Z]+$";
    private static String usernamePattern = "^[a-zA-Z0-9]+$";

    private static String ownerPattern = "^[a-zA-Z\\s'-]+$";

    public static Boolean isValidPassword(String password){

        return Pattern.matches(passwordPattern, password);

    }

    public static Boolean isValidTaxCode(String taxCode){

        return Pattern.matches(taxCodePattern, taxCode);

    }

    public static Boolean isValidEmail(String email){

        return Pattern.matches(emailPattern, email);

    }

    public static boolean isValidName(String name) {

        return Pattern.matches(namePattern, name);
    }

    public static boolean isValidUsername(String username) {

        return Pattern.matches(usernamePattern, username);
    }

    public static boolean isValidOwner(String owner) {
        return Pattern.matches(ownerPattern, owner);
    }

}
