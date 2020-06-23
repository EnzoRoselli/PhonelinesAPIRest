package com.utn.UTNphones.controllers.webs;

import com.utn.UTNphones.domains.dto.responses.AttributesResponseErrorDto;
import com.utn.UTNphones.domains.dto.responses.ErrorResponseDTO;
import com.utn.UTNphones.exceptions.SearchByDatesException;
import com.utn.UTNphones.exceptions.cityExceptions.CityNotExists;
import com.utn.UTNphones.exceptions.phonelineExceptions.IlegalUserForPhoneline;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineAlreadyExists;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineDigitsCountPlusPrefix;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineNotExists;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelineTypeError;
import com.utn.UTNphones.exceptions.phonelineExceptions.PhonelinesNotRegisteredByUser;
import com.utn.UTNphones.exceptions.rateExceptions.RateNotExists;
import com.utn.UTNphones.exceptions.usersExceptions.LogException;
import com.utn.UTNphones.exceptions.usersExceptions.UserNotExists;
import com.utn.UTNphones.exceptions.usersExceptions.UserTypeNotExists;
import com.utn.UTNphones.exceptions.usersExceptions.UserTypeWithIdentificationAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotExists.class)
    public ErrorResponseDTO handleUserDoesNotExists(UserNotExists ex) {
        return new ErrorResponseDTO(1, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LogException.class)
    public ErrorResponseDTO handleLogException(LogException ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 2);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CityNotExists.class)
    public ErrorResponseDTO handleCityDoesntExist(CityNotExists ex) {
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
    @ExceptionHandler(PhonelineNotExists.class)
    public ErrorResponseDTO handlePhonelineDoesntExist(PhonelineNotExists ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 6);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhonelinesNotRegisteredByUser.class)
    public ErrorResponseDTO handlePhonelinesNotRegisteredByUser(PhonelinesNotRegisteredByUser ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 7);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserTypeNotExists.class)
    public ErrorResponseDTO handleUserTypeDoesntExist(UserTypeNotExists ex) {
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
    @ExceptionHandler(SearchByDatesException.class)
    public ErrorResponseDTO handleSearchByDatesException(SearchByDatesException ex){
        return ErrorResponseDTO.fromRunTimeException(ex,16);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RateNotExists.class)
    public ErrorResponseDTO handleRateDoesntExist(RateNotExists ex) {
        return ErrorResponseDTO.fromRunTimeException(ex, 14);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AttributesResponseErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return AttributesResponseErrorDto.fromMethodArgumentNotValidException(ex, 15);
    }

}
