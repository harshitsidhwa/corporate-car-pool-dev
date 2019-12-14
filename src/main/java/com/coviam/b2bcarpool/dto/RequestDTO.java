package com.coviam.b2bcarpool.dto;

public class RequestDTO<T> {
    private String userId;
    private T requestContent;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public T getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(T requestContent) {
        this.requestContent = requestContent;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "userId='" + userId + '\'' +
                ", requestContent=" + requestContent +
                '}';
    }
}
