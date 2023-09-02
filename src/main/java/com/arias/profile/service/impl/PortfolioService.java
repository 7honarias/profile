package com.arias.profile.service.impl;

import com.arias.profile.dtos.request.PortfolioRequestDTO;
import com.arias.profile.dtos.response.PortfolioResponseDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import com.arias.profile.models.Portfolio;
import com.arias.profile.repositories.PortfolioRepository;
import com.arias.profile.service.IPortfolioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PortfolioService implements IPortfolioService {

    @Value("${default.portfolio.image}")
    private String defaultImage;

    private final PortfolioRepository portfolioRepository;

    private final ObjectMapper objectMapper;

    public PortfolioService(PortfolioRepository portfolioRepository, ObjectMapper objectMapper) {
        this.portfolioRepository = portfolioRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolioRequestDTO) {
        log.info("Start service createPortfolio");
        Portfolio portfolio = objectMapper.convertValue(portfolioRequestDTO, Portfolio.class);

        if(portfolioRequestDTO.getImageUrl() == null) {
            portfolio.setImageUrl(defaultImage);
        }
        portfolioRepository.save(portfolio);
        log.info("Portfolio created successfully");

        return objectMapper.convertValue(portfolio, PortfolioResponseDTO.class);

    }

    @Override
    public PortfolioResponseDTO getPortfolio(Long id) throws BusinessRuleException {

        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException(String.format("Portfolio with id: %d not found", id)));

        PortfolioResponseDTO responseDTO = objectMapper.convertValue(portfolio, PortfolioResponseDTO.class);
        log.info("service end successfully");
        return responseDTO;
    }

    @Override
    public PortfolioResponseDTO updatePortfolio(Long id, PortfolioRequestDTO portfolioRequestDTO)
            throws BusinessRuleException {

        log.info("Start service Update portfolio");

        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException(String.format("Portfolio with id: %d not found", id)));

        parseDocument(portfolio, portfolioRequestDTO);
        portfolioRepository.save(portfolio);
        log.info("Portfolio updated successfully");

        return objectMapper.convertValue(portfolio, PortfolioResponseDTO.class);
    }

    private void parseDocument(Portfolio portfolio, PortfolioRequestDTO portfolioRequestDTO) {
        log.info("mapping portfolio to update");
        if(!portfolio.getDescription().equals(portfolioRequestDTO.getDescription())){
            portfolio.setDescription(portfolioRequestDTO.getDescription());
        }
        if(!portfolio.getOccupation().equals(portfolioRequestDTO.getOccupation())) {
            portfolio.setOccupation(portfolioRequestDTO.getOccupation());
        }
        if(!portfolio.getGithubUserName().equals(portfolioRequestDTO.getGithubUserName())) {
            portfolio.setGithubUserName(portfolioRequestDTO.getGithubUserName());
        }
        if(portfolioRequestDTO.getImageUrl() != null &&
                !portfolio.getImageUrl().equals(portfolioRequestDTO.getImageUrl())) {
            portfolio.setImageUrl(portfolioRequestDTO.getImageUrl());
        }
        if(!portfolio.getUserName().equals(portfolioRequestDTO.getUserName())){
            portfolio.setUserName(portfolioRequestDTO.getUserName());
        }

    }
}
