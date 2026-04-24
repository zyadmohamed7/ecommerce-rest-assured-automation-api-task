package org.example.dto.responses;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String access_token;
    private String refresh_token;
}
