package org.example.dto.responses;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {
    private Integer id;
    private String name;
    private String address;
}
