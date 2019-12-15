package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class SignUpResponseDTO {
    private String userId;
    private String username;
    private boolean signUpSuccess;
}
