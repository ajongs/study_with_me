package controller;

import enums.ErrorEnum;
import exception.NotFoundFileException;
import exception.UnAuthorizedException;
import exception.unauthenticateException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
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
    //권한 에러(
    @ExceptionHandler(UnAuthorizedException.class)
    public @ResponseBody ResponseEntity AuthorizationError(UnAuthorizedException e){
        return new ResponseEntity(ErrorEnum.UNAUTHORIZED, HttpStatus.BAD_REQUEST);
    }

    //서명 에러
    @ExceptionHandler(SignatureException.class)
    public @ResponseBody ResponseEntity SignatureException(SignatureException e){
        return new ResponseEntity(ErrorEnum.SIGNATURE, HttpStatus.BAD_REQUEST);
    }
    //만료 에러
    @ExceptionHandler(ExpiredJwtException.class)
    public @ResponseBody ResponseEntity ExpiredException(ExpiredJwtException e){
        return new ResponseEntity(ErrorEnum.EXPIRED, HttpStatus.BAD_REQUEST);
    }
    //토큰 구조 에러
    @ExceptionHandler(MalformedJwtException.class)
    public @ResponseBody ResponseEntity MalformedException(MalformedJwtException e){
        return new ResponseEntity(ErrorEnum.MALFORMED, HttpStatus.BAD_REQUEST);
    }
    //파일 null 일때 에러
    @ExceptionHandler(NotFoundFileException.class)
    public @ResponseBody ResponseEntity NotFoundFileException(NotFoundFileException e){
        return new ResponseEntity(ErrorEnum.NOTFOUNDFILE, HttpStatus.BAD_REQUEST);
    }
}
