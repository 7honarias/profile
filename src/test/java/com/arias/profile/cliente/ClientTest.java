package com.arias.profile.cliente;

import com.arias.profile.client.Client;
import com.arias.profile.dtos.response.RepositoryDTO;
import com.arias.profile.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    private Client client;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(client, "token", "jdhsdfla");
        ReflectionTestUtils.setField(client, "apiUrl", "http://test.com/");
    }

    @Test
    public void testGetReposRequest_whenRequestIsCorrect_shouldReturnListOfRepositoryDTOs() throws BusinessRuleException {
        List<RepositoryDTO> dummyResponse = new ArrayList<>();

        ResponseEntity<List<RepositoryDTO>> expectedResponseEntity = new ResponseEntity<>(dummyResponse, HttpStatus.OK);

        when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(new ParameterizedTypeReference<List<RepositoryDTO>>(){})))
                .thenReturn(expectedResponseEntity);

        ResponseEntity<List<RepositoryDTO>> actualResponse = client.getReposRequest("username");

        assertEquals(dummyResponse, actualResponse.getBody());
    }

    @Test
    public void testGetReposRequest_whenRequestFail_shouldThrowBusinessRule() throws BusinessRuleException {

        HttpStatusCodeException httpStatusCodeException = new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {
            @Override
            public HttpStatus getStatusCode() {
                return super.getStatusCode();
            }
        };
        doThrow(httpStatusCodeException).when(restTemplate).exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(new ParameterizedTypeReference<List<RepositoryDTO>>(){}));

        assertThrows(BusinessRuleException.class, () -> client.getReposRequest("userName"));
    }


}