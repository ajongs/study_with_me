package enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorEnum {
    VALIDATION_FAIL(1, "입력값을 다시 확인하여 주십시오. 올바르지 못한 타입입니다", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(2, "유효하지 않은 토큰입니다.", HttpStatus.BAD_REQUEST),
    UNATHORIZED(4, "권한이 없습니다.", HttpStatus.BAD_REQUEST);

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
