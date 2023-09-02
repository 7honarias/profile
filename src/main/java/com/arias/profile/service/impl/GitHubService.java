package com.arias.profile.service.impl;

import com.arias.profile.client.Client;
import com.arias.profile.dtos.response.GeneralResponseDTO;
import com.arias.profile.dtos.response.RepositoryDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import com.arias.profile.service.IGitHubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GitHubService implements IGitHubService {

    private final Client client;

    public GitHubService(Client client) {
        this.client = client;
    }

    public GeneralResponseDTO<List<RepositoryDTO>> getReposByUser(String userName) throws BusinessRuleException {
        log.info("Service getReposByUser");
        GeneralResponseDTO<List<RepositoryDTO>> responseDTO = new GeneralResponseDTO<>();

        ResponseEntity<List<RepositoryDTO>> response = client.getReposRequest(userName);
        List<RepositoryDTO> repositoryDTOS = response.getBody();
        if (repositoryDTOS.size() > 10) {
            repositoryDTOS = repositoryDTOS.subList(0, 9);
        }
        responseDTO.setData(repositoryDTOS);
        log.info("request success");

        return responseDTO;
    }
}