package com.arias.profile.service;

import com.arias.profile.dtos.request.PortfolioRequestDTO;
import com.arias.profile.dtos.response.PortfolioResponseDTO;
import com.arias.profile.exceptions.BusinessRuleException;

public interface IPortfolioService {
       PortfolioResponseDTO createPortfolio(PortfolioRequestDTO portfolioRequestDTO);

       PortfolioResponseDTO getPortfolio(Long id) throws BusinessRuleException;

       PortfolioResponseDTO updatePortfolio(Long id, PortfolioRequestDTO portfolioRequestDTO) throws BusinessRuleException;
}
