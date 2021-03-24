package com.gb.homework.util;

import com.gb.homework.exception.exceptions.WrongInputException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InputValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public void inputLengthValidation(String input, Integer maxLength, String errorMessage) throws WrongInputException {
      if (input.length() > maxLength) {
          throw new WrongInputException(errorMessage, new Throwable());
      }
    };

    public void emailValidator(String email) throws WrongInputException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            throw new WrongInputException("wrong email format", new Throwable());
        }
    }
}
