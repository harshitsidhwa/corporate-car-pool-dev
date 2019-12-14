package com.coviam.b2bcarpool.dto;

public class ResponseDTO<T> {
    private boolean success;
    private String errorMessage;
    private T response;

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

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "status='" + success + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", response=" + response +
                '}';
    }
}
