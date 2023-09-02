package com.arias.profile.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRequestDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String occupation;

    @NotBlank
    private String description;

    private String imageUrl;

    @NotBlank
    private String githubUserName;
}
