package com.utn.UTNphones.Controllers.Web;

import com.utn.UTNphones.Dto.ErrorResponseDto;
import com.utn.UTNphones.Exceptions.UserExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserExceptions.class)
    public ErrorResponseDto handleUserNotExists() {
        return new ErrorResponseDto(3, "User doesn´t exist");
    }
}
