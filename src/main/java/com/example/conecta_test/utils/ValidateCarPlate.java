package com.example.conecta_test.utils;

import java.util.regex.Pattern;

public class ValidateCarPlate {
    public static boolean validatePlate(String plate){
        String regex = "^[A-Z]{3}\\d{3,4}$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(plate).matches();
    }
}
