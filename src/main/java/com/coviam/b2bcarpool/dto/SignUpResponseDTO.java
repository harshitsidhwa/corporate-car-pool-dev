package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class SignUpResponseDTO {
    private String userId;
    private boolean signUpSuccess;
}
