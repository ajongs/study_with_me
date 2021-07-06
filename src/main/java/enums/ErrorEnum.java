package enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorEnum {
    VALIDATION_FAIL(1, "입력값을 다시 확인하여 주십시오. 올바르지 못한 타입입니다", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(2, "유효하지 않은 토큰입니다.", HttpStatus.BAD_REQUEST),
    EXPIRED(3, "만료된 토큰입니다. 토큰을 재발급 받으십시오.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(4, "권한이 없습니다.", HttpStatus.BAD_REQUEST),
    SIGNATURE(5,"토큰의 서명이 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    MALFORMED(6, "올바르지 않는 토큰의 구조입니다.", HttpStatus.BAD_REQUEST),
    NOTFOUNDFILE(7, "업로드 하려는 파일이 없습니다.", HttpStatus.BAD_REQUEST);

    private int errorCode;
    private String message;
    private HttpStatus httpStatus;

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    ErrorEnum(int errorCode, String message, HttpStatus httpStatus){
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
