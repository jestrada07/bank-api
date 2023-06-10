package com.gradientbankapi.bankapi.code_response;

public class CodeMessageFactor {

    //Retrieve the appropriate code
    private Integer code;
    //Write a message pertaining to a specific code
    private String message;
    //Get the data once it succeeds
    private Object data;

    public CodeMessageFactor() {}
    public CodeMessageFactor(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public CodeMessageFactor(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
