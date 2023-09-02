package com.arias.profile.controller;


import com.arias.profile.dtos.response.GeneralResponseDTO;
import com.arias.profile.dtos.response.RepositoryDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import com.arias.profile.service.IGitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/github")
public class GitHubController {

    private final IGitHubService gitHubApiService;

    public GitHubController(IGitHubService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping("/repos/{username}")
    public ResponseEntity<GeneralResponseDTO<List<RepositoryDTO>>> getReposByUser(@PathVariable String username)
            throws BusinessRuleException {

        GeneralResponseDTO<List<RepositoryDTO>> repos = gitHubApiService.getReposByUser(username);

        return ResponseEntity.ok(repos);
    }
}
