package com.coviam.b2bcarpool.dto;

public class ResponseDTO<T> {
    private boolean success;
    private String errorMessage;
    private T responseContent;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(T responseContent) {
        this.responseContent = responseContent;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "status='" + success + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", responseContent=" + responseContent +
                '}';
    }
}
