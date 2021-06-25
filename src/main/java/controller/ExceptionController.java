package controller;

import enums.ErrorEnum;
import exception.unauthenticateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(unauthenticateException.class)
    public @ResponseBody ResponseEntity Error(unauthenticateException e){
        return new ResponseEntity(ErrorEnum.INVALID_TOKEN, HttpStatus.BAD_REQUEST);
    }
}
