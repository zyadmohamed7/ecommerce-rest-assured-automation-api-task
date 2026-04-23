package org.example.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private List<String> images;
    private CategoryResponse category;
    private String slug;
    private String creationAt;
    private String updatedAt;
}
