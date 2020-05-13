package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Dto.ErrorResponseDto;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserDoesntExist;
import com.utn.UTNphones.Exceptions.UsersExceptions.UserExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDoesntExist.class)
    public ErrorResponseDto handleUserNotExists(UserDoesntExist ude) {
        return new ErrorResponseDto(3, ude.getMessage(),ude.getCause());
    }
}
