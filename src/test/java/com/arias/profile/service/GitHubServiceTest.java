package com.arias.profile.service;

import com.arias.profile.client.Client;
import com.arias.profile.dtos.response.GeneralResponseDTO;
import com.arias.profile.dtos.response.RepositoryDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import com.arias.profile.service.impl.GitHubService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GitHubServiceTest {

    @Mock
    private Client client;

    @InjectMocks
    private GitHubService gitHubService;


    @Test
    void testGetReposByUser() throws BusinessRuleException {
        GeneralResponseDTO<List<RepositoryDTO>> generalResponseDTO = new GeneralResponseDTO<>();
        ResponseEntity<List<RepositoryDTO>> response =
                new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

        when(client.getReposRequest(any())).thenReturn(response);

        generalResponseDTO = gitHubService.getReposByUser("userName");

        assertEquals(generalResponseDTO.getData(), new ArrayList<>());

    }

}
