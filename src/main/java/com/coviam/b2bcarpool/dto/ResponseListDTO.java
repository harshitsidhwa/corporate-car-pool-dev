package com.coviam.b2bcarpool.dto;

import java.util.List;

public class ResponseListDTO<T> {
    private boolean success;
    private String errorMessage;
    private List<T> responseContent;

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

    public List<T> getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(List<T> responseContent) {
        this.responseContent = responseContent;
    }

    @Override
    public String toString() {
        return "ResponseListDTO{" +
                "success='" + success + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", responseContent=" + responseContent +
                '}';
    }
}
