package enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {

    VALIDATION_FAIL(1, "입력값을 다시 확인하여 주십시오. 올바르지 못한 타입입니다", HttpStatus.BAD_REQUEST);


    int errorCode;
    String message;
    HttpStatus httpStatus;

    ErrorEnum(int errorCode, String message, HttpStatus httpStatus){
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
