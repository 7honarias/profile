package com.arias.profile.controller;

import com.arias.profile.dtos.request.PortfolioRequestDTO;
import com.arias.profile.dtos.response.GeneralResponseDTO;
import com.arias.profile.dtos.response.PortfolioResponseDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import com.arias.profile.service.IPortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin
@RequestMapping("/portfolio")
@Slf4j
public class PortfolioController {

    private final IPortfolioService portfolioService;

    public PortfolioController(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }


    @PostMapping("/create")
    public ResponseEntity<GeneralResponseDTO<PortfolioResponseDTO>> createPortfolio(
            @Valid @RequestBody PortfolioRequestDTO portfolioRequestDTO) {

        log.info("Start Controller Create Portfolio");
        PortfolioResponseDTO responseDTO = portfolioService.createPortfolio(portfolioRequestDTO);

        ResponseEntity<GeneralResponseDTO<PortfolioResponseDTO>> response = ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GeneralResponseDTO.<PortfolioResponseDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("Portfolio created successfully")
                        .data(responseDTO)
                        .build());
        log.info("end controller create");

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO<PortfolioResponseDTO>> getPortfolioById(
            @PathVariable("id") @Valid @NotNull @Min(1) Long id) throws BusinessRuleException {

        log.info("Start Controller get Portfolio by id");
        PortfolioResponseDTO responseDTO = portfolioService.getPortfolio(id);

        ResponseEntity<GeneralResponseDTO<PortfolioResponseDTO>> response = ResponseEntity
                .status(HttpStatus.OK)
                .body(GeneralResponseDTO.<PortfolioResponseDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("Portfolio obtained successfully")
                        .data(responseDTO)
                        .build());
        log.info("end controller get portfolio by id");

        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralResponseDTO<PortfolioResponseDTO>> updatePortfolio(
            @PathVariable("id") Long id, @Valid @RequestBody PortfolioRequestDTO portfolioRequestDTO) throws BusinessRuleException {

        log.info("Start Controller get Portfolio by id");
        PortfolioResponseDTO responseDTO = portfolioService.updatePortfolio(id, portfolioRequestDTO);

        ResponseEntity<GeneralResponseDTO<PortfolioResponseDTO>> response = ResponseEntity
                .status(HttpStatus.OK)
                .body(GeneralResponseDTO.<PortfolioResponseDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("Portfolio updated successfully")
                        .data(responseDTO)
                        .build());
        log.info("end controller update");
        return response;
    }


}
