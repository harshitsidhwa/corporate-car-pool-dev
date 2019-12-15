package com.coviam.b2bcarpool.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String emailId;
    private String password;
}
