package com.arias.profile.client;

import com.arias.profile.dtos.response.RepositoryDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Repository
public class Client {

    private final RestTemplate restTemplate;

    @Value("${github.secret.token}")
    private String token;

    @Value("${github.users.url}")
    private String apiUrl;

    public Client(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<RepositoryDTO>> getReposRequest(String userName)
            throws BusinessRuleException {
        ResponseEntity<List<RepositoryDTO>> response;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        String url = apiUrl + userName + "/repos";

        HttpEntity<RepositoryDTO> request = new HttpEntity<>(headers);
        try {
            response = restTemplate
                    .exchange(url,
                            HttpMethod.GET,
                            request,
                            new ParameterizedTypeReference<>() {
                            });
        } catch (HttpStatusCodeException e) {
            log.info("Request fail " + e.getMessage());
            throw new BusinessRuleException("request to github Error");
        }
        return response;

    }
}
