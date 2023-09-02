package com.arias.profile.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseDTO<T>  {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "error")
    private List<String> error;

    @JsonProperty(value = "data")
    private T data;

}
