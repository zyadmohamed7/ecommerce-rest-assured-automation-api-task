package org.example.dto.responses;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileResponse {
    private String originalname;
    private String filename;
    private String location;
}
