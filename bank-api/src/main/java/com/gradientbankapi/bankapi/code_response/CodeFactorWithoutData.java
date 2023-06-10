package com.gradientbankapi.bankapi.code_response;

public class CodeFactorWithoutData {

    private Integer code;
    private String message;

    public CodeFactorWithoutData() {}
    public CodeFactorWithoutData(Integer code, String message) {
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

}
