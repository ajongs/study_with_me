package controller;

import enums.ErrorEnum;
import exception.UnAuthorizedException;
import exception.unauthenticateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(unauthenticateException.class)
    public @ResponseBody ResponseEntity authenticationError(unauthenticateException e){
        return new ResponseEntity(ErrorEnum.INVALID_TOKEN, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public @ResponseBody ResponseEntity AuthorizationError(UnAuthorizedException e){
        return new ResponseEntity(ErrorEnum.UNATHORIZED, HttpStatus.BAD_REQUEST);
    }
}
