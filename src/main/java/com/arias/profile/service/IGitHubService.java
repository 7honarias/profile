package com.arias.profile.service;

import com.arias.profile.dtos.response.GeneralResponseDTO;
import com.arias.profile.dtos.response.RepositoryDTO;
import com.arias.profile.exceptions.BusinessRuleException;

import java.util.List;

public interface IGitHubService {
    GeneralResponseDTO<List<RepositoryDTO>> getReposByUser(String userName) throws BusinessRuleException;
}
