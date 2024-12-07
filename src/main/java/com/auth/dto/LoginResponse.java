package com.auth.dto;

import com.auth.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
	private String token;

    private Long expiresIn;
    private User user;

    public String getToken() {
        return token;
    }
}
