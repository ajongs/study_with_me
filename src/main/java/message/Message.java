package message;

import enums.ErrorEnum;

public class Message {
    private ErrorEnum errorCode;
    private String data;

    public Message() {
        this.errorCode = null;
        this.data = null;
    }

    public ErrorEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorEnum errorCode) {
        this.errorCode = errorCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
