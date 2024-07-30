package com.example.composemvvm

import java.util.regex.Pattern

object Validations {

    fun validateEmail(value: String): Boolean {
        return !(android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches())
    }
    fun validatePassword(value: String): Boolean {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$");
        return !(passwordREGEX.matcher(value).matches())
    }

    fun validateName(value: String): Boolean {
        val nameREGEX = Pattern.compile("^([a-zA-Z]{2,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)");
        return !(nameREGEX.matcher(value).matches())
    }

}