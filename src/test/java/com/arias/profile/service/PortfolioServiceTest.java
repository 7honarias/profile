package com.arias.profile.service;

import com.arias.profile.dtos.request.PortfolioRequestDTO;
import com.arias.profile.dtos.response.PortfolioResponseDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import com.arias.profile.models.Portfolio;
import com.arias.profile.repositories.PortfolioRepository;
import com.arias.profile.service.impl.PortfolioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    void testPortfolioService_whenCreatePortfolio() {
        PortfolioRequestDTO portfolioRequestDTO = getPortfolioRequest();
        Portfolio portfolio = getPortfolio();

        when(objectMapper.convertValue(portfolioRequestDTO, Portfolio.class))
                .thenReturn(portfolio);

        when(objectMapper.convertValue(portfolio, PortfolioResponseDTO.class))
                .thenReturn(new PortfolioResponseDTO());

        PortfolioResponseDTO responseDTO = portfolioService.createPortfolio(portfolioRequestDTO);

        assertEquals(responseDTO.getClass(), PortfolioResponseDTO.class);
    }

    @Test
    void testPortfolioService_whenGetPortfolio() throws BusinessRuleException {
        Portfolio portfolio = getPortfolio();
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(getPortfolio()));
        when(objectMapper.convertValue(portfolio, PortfolioResponseDTO.class))
                .thenReturn(new PortfolioResponseDTO());

        PortfolioResponseDTO portfolioResponseDTO = portfolioService.getPortfolio(1L);

        assertEquals(portfolioResponseDTO.getClass(), PortfolioResponseDTO.class);
    }

    @Test
    void testPortfolioService_whenUpdatePortfolio() throws BusinessRuleException {
        PortfolioRequestDTO portfolioRequestDTO = getPortfolioRequest();
        Portfolio portfolio = getPortfolio();
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(getPortfolio()));
        when(objectMapper.convertValue(portfolio, PortfolioResponseDTO.class))
                .thenReturn(PortfolioResponseDTO.builder().build());

        PortfolioResponseDTO portfolioResponseDTO = portfolioService.updatePortfolio(1L, portfolioRequestDTO);

        assertEquals(portfolioResponseDTO.getClass(), PortfolioResponseDTO.class);


    }

    private PortfolioRequestDTO getPortfolioRequest() {
        return PortfolioRequestDTO
                .builder().description("description")
                .userName("yo")
                .githubUserName("userName")
                .imageUrl("image")
                .occupation("developer")
                .build();

    }

    private Portfolio getPortfolio() {
        return Portfolio.builder()
                .id(1L)
                .userName("yo")
                .description("description")
                .githubUserName("userName")
                .imageUrl("image")
                .occupation("developer")
                .build();

    }

}
