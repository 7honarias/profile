package com.arias.profile.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortfolioResponseDTO {

    private Long id;

    private String userName;

    private String occupation;

    private String description;

    private String imageUrl;

    private String githubUserName;
}
