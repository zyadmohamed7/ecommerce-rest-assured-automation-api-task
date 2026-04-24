package org.example.dto.responses;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String role;
    private String avatar;
    private String creationAt;
    private String updatedAt;
}
