package org.example.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequest {
    private String name;
    private String address;
}
