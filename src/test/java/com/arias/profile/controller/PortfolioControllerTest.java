package com.arias.profile.controller;

import com.arias.profile.dtos.response.GeneralResponseDTO;
import com.arias.profile.dtos.response.PortfolioResponseDTO;
import com.arias.profile.service.IPortfolioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PortfolioController.class)
public class PortfolioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPortfolioService portfolioService;

    @Test
    public void testGetPortfolioByUser() throws Exception {
        PortfolioResponseDTO portfolio = new PortfolioResponseDTO();
        GeneralResponseDTO<PortfolioResponseDTO> response = new GeneralResponseDTO<>();

        response.setData(portfolio);
        Mockito.when(portfolioService.getPortfolio(1L)).thenReturn(portfolio);

        mockMvc.perform(MockMvcRequestBuilders.get("/portfolio/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"data\": {\"id\":null,\"userName\":null,\"occupation\":null,\"description\":null,\"imageUrl\":null,\"githubUserName\":null},\"message\":\"Portfolio obtained successfully\"}"));
    }

    @Test
    public void testCreatePortfolioByUser() throws Exception {
        PortfolioResponseDTO portfolio = new PortfolioResponseDTO();
        GeneralResponseDTO<PortfolioResponseDTO> response = new GeneralResponseDTO<>();
        String requestBody = "{\"userName\":\"exampleUser\",\"occupation\":\"developer\",\"description\":\"Portfolio description\",\"imageUrl\":\"imageURL\",\"githubUserName\":\"githubUser\"}";
        response.setData(portfolio);
        Mockito.when(portfolioService.getPortfolio(1L)).thenReturn(portfolio);

        mockMvc.perform(MockMvcRequestBuilders.post("/portfolio/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"data\": null,\"message\":\"Portfolio created successfully\"}"));
    }


    @Test
    public void testUpdatePortfolioByUser() throws Exception {
        PortfolioResponseDTO portfolio = new PortfolioResponseDTO();
        GeneralResponseDTO<PortfolioResponseDTO> response = new GeneralResponseDTO<>();
        String requestBody = "{\"userName\":\"exampleUser\",\"occupation\":\"developer\",\"description\":\"Portfolio description\",\"imageUrl\":\"imageURL\",\"githubUserName\":\"githubUser\"}";
        response.setData(portfolio);
        Mockito.when(portfolioService.getPortfolio(1L)).thenReturn(portfolio);

        mockMvc.perform(MockMvcRequestBuilders.put("/portfolio/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"data\": null,\"message\":\"Portfolio updated successfully\"}"));
    }
}
