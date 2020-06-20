package com.utn.UTNphones.Controllers.Webs;

import com.utn.UTNphones.Domains.Dto.Responses.AttributesResponseErrorDto;
import com.utn.UTNphones.Domains.Dto.Responses.ErrorResponseDTO;
import com.utn.UTNphones.Exceptions.CityExceptions.CityDoesntExist;
import com.utn.UTNphones.Exceptions.PhonelineExceptions.IlegalUserForPhoneline;
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
import com.utn.UTNphones.Exceptions.UsersExceptions.UserTypeWithIdentificationAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDoesntExist.class)
    public ErrorResponseDTO handleUserNotExists(UserDoesntExist ex) {
        return new ErrorResponseDTO(1, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LogException.class)
    public ErrorResponseDTO handleParametersException(LogException ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 2);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CityDoesntExist.class)
    public ErrorResponseDTO handleCityDoesntExist(CityDoesntExist ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 3);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineAlreadyExists.class)
    public ErrorResponseDTO handlePhonelineAlreadyExists(PhonelineAlreadyExists ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 4);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDigitsCountPlusPrefix.class)
    public ErrorResponseDTO handlePhonelineDigitsCountPlusPrefix(PhonelineDigitsCountPlusPrefix ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 5);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineDoesntExist.class)
    public ErrorResponseDTO handlePhonelineDoesntExist(PhonelineDoesntExist ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 6);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelinesNotRegisteredByUser.class)
    public ErrorResponseDTO handlePhonelinesNotRegisteredByUser(PhonelinesNotRegisteredByUser ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 7);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserIdentificationAlreadyExists.class)
    public ErrorResponseDTO handleUserIdentificationAlreadyExists(UserIdentificationAlreadyExists ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 8);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserTypeDoesntExist.class)
    public ErrorResponseDTO handleUserTypeDoesntExist(UserTypeDoesntExist ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 9);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IlegalUserForPhoneline.class)
    public ErrorResponseDTO handleIlegalUserForPhoneline(IlegalUserForPhoneline ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 11);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserTypeWithIdentificationAlreadyExists.class)
    public ErrorResponseDTO handleUserTypeWithIdentificationAlreadyExists(UserTypeWithIdentificationAlreadyExists ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 12);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelineTypeError.class)
    public ErrorResponseDTO handlePhonelineTypeError(PhonelineTypeError ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 13);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RateDoesntExist.class)
    public ErrorResponseDTO handleRateDoesntExist(RateDoesntExist ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 14);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AttributesResponseErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return AttributesResponseErrorDto.fromMethodArgumentNotValidException(ex, 15);
    }

}
