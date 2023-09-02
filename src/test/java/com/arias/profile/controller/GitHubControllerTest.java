package com.arias.profile.controller;

import com.arias.profile.dtos.response.GeneralResponseDTO;
import com.arias.profile.dtos.response.RepositoryDTO;
import com.arias.profile.service.IGitHubService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(GitHubController.class)
public class GitHubControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGitHubService gitHubApiService;

    @Test
    public void testGetReposByUser() throws Exception {
        String username = "exampleUser";
        List<RepositoryDTO> dummyRepositories = new ArrayList<>();
        GeneralResponseDTO<List<RepositoryDTO>> response = new GeneralResponseDTO<>();

        response.setData(dummyRepositories);
        Mockito.when(gitHubApiService.getReposByUser(username)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/github/repos/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"data\":[],\"message\":null}")); // Verifica la respuesta esperada
    }
}