package com.arias.profile.dtos.response;

import lombok.Data;

import java.util.Date;

@Data
public class RepositoryDTO {

    private String name;
    private String description;
    private String url;
    private String language;
    private int stars;
    private int forks;
    private Date lastCommit;
    private String author;
}
