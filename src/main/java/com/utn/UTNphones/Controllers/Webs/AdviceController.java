package com.utn.UTNphones.Controllers.Webs;

import com.utn.UTNphones.Domains.Dto.ErrorResponseDTO;
import com.utn.UTNphones.Exceptions.CallExceptions.CallException;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.ParametersException;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.Exceptions.RateExceptions.RateDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.LogException;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserIdentificationAlreadyExists;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeDoesntExist;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDoesntExist.class)
    public ErrorResponseDTO handleUserNotExists(UserDoesntExist ex) {
        return new ErrorResponseDTO(1, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParametersException.class)
    public ErrorResponseDTO handleParametersException(ParametersException ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LogException.class)
    public ErrorResponseDTO handleParametersException(LogException ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CityDoesntExist.class)
    public ErrorResponseDTO handleCityDoesntExist(CityDoesntExist ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineAlreadyExists.class)
    public ErrorResponseDTO handlePhonelineAlreadyExists(PhonelineAlreadyExists ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDigitsCountPlusPrefix.class)
    public ErrorResponseDTO handlePhonelineDigitsCountPlusPrefix(PhonelineDigitsCountPlusPrefix ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDoesntExist.class)
    public ErrorResponseDTO handlePhonelineDoesntExist(PhonelineDoesntExist ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelinesNotRegisteredByUser.class)
    public ErrorResponseDTO handlePhonelinesNotRegisteredByUser(PhonelinesNotRegisteredByUser ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserIdentificationAlreadyExists.class)
    public ErrorResponseDTO handleUserIdentificationAlreadyExists(UserIdentificationAlreadyExists ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserTypeDoesntExist.class)
    public ErrorResponseDTO handleUserTypeDoesntExist(UserTypeDoesntExist ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CallException.class)
    public ErrorResponseDTO handleCallException(CallException ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineTypeError.class)
    public ErrorResponseDTO handlePhonelineTypeError(PhonelineTypeError ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RateDoesntExist.class)
    public ErrorResponseDTO handleRateDoesntExist(RateDoesntExist ex) {
        return new ErrorResponseDTO(2, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
