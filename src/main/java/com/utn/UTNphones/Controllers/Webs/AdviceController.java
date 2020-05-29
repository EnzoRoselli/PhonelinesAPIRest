package com.utn.UTNphones.Controllers.Webs;

import com.utn.UTNphones.Domains.Dto.ErrorResponseDto;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.*;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserIdentificationAlreadyExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeDoesntExist;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDoesntExist.class)
    public ErrorResponseDto handleUserNotExists(UserDoesntExist ex) {
        return new ErrorResponseDto(1, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParametersException.class)
    public ErrorResponseDto handleParametersException(ParametersException ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LogException.class)
    public ErrorResponseDto handleParametersException(LogException ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CityDoesntExist.class)
    public ErrorResponseDto handleCityDoesntExist(CityDoesntExist ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineAlreadyExists.class)
    public ErrorResponseDto handlePhonelineAlreadyExists(PhonelineAlreadyExists ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDigitsCountPlusPrefix.class)
    public ErrorResponseDto handlePhonelineDigitsCountPlusPrefix(PhonelineDigitsCountPlusPrefix ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDoesntExist.class)
    public ErrorResponseDto handlePhonelineDoesntExist(PhonelineDoesntExist ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelinesNotRegisteredByUser.class)
    public ErrorResponseDto handlePhonelinesNotRegisteredByUser(PhonelinesNotRegisteredByUser ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserIdentificationAlreadyExists.class)
    public ErrorResponseDto handleUserIdentificationAlreadyExists(UserIdentificationAlreadyExists ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserTypeDoesntExist.class)
    public ErrorResponseDto handleUserTypeDoesntExist(UserTypeDoesntExist ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CallException.class)
    public ErrorResponseDto handleCallException(CallException ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseDto handleConstraintViolationException(ConstraintViolationException ex) {

        return new ErrorResponseDto(2, PatternsHandler(ex));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineTypeError.class)
    public ErrorResponseDto handlePhonelineTypeError(PhonelineTypeError ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    public ErrorResponseDto handleTransactionSystemException(TransactionSystemException ex) {
       return new ErrorResponseDto(2,  AdviceController.PatternsHandler((ConstraintViolationException) ex.getCause().getCause()));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RateDoesntExist.class)
    public ErrorResponseDto handleRateDoesntExist(RateDoesntExist ex) {
        return new ErrorResponseDto(2, ex.getMessage());
    }



    public static String PatternsHandler(ConstraintViolationException message) {
        Pattern pattern = Pattern.compile("'.*?'");
        Matcher matcher = pattern.matcher(message.getMessage());
        StringBuilder PatternErrors = new StringBuilder("Errors with the validation with: ");
        while (matcher.find()) {
            switch (matcher.group()) {
                case "'Invalid lastname!'":
                    if (!PatternErrors.toString().contains("'Invalid lastname!'")) PatternErrors.append(matcher.group()).append(" - ");
                    break;
                case "'Invalid identification!'":
                    if (!PatternErrors.toString().contains("'Invalid identification!'")) PatternErrors.append(matcher.group()).append(" - ");
                    break;
                case "'Invalid name!'":
                    if (!PatternErrors.toString().contains("'Invalid name!'")) PatternErrors.append(matcher.group()).append(" - ");
                    break;
                case "'Invalid number!'":
                    if (!PatternErrors.toString().contains("'Invalid number!'")) PatternErrors.append(matcher.group()).append(" - ");
                    break;
            }
        }
        return PatternErrors.toString();
    }

}
