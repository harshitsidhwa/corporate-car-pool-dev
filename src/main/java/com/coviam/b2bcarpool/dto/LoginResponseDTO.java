package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String userId;
    private boolean loginSuccess;
    private String errMsg;
}
