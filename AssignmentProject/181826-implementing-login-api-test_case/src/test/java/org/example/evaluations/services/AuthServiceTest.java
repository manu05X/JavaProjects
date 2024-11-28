package org.example.evaluations.services;

import org.example.evaluations.evaluation.dtos.FakeStoreLoginRequestDto;
import org.example.evaluations.evaluation.dtos.FakeStoreLoginResponseDto;
import org.example.evaluations.evaluation.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    void testLogin_Success() {
        // Arrange
        FakeStoreLoginRequestDto requestDto = new FakeStoreLoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("password");

        FakeStoreLoginResponseDto responseDto = new FakeStoreLoginResponseDto();
        responseDto.setToken("test-token");

        ResponseEntity<FakeStoreLoginResponseDto> responseEntity = ResponseEntity.ok(responseDto);

        when(restTemplate.postForEntity(any(String.class), any(), any(Class.class)))
                .thenReturn(responseEntity);

        MultiValueMap<String, String> expectedHeaders = new LinkedMultiValueMap<>();
        expectedHeaders.add(HttpHeaders.SET_COOKIE, "test-token");

        // Act
        MultiValueMap<String, String> actualHeaders = authService.login(requestDto);

        // Assert
        assertEquals(expectedHeaders, actualHeaders);
    }

    @Test
    void testLogin_HttpClientErrorException() {
        // Arrange
        FakeStoreLoginRequestDto requestDto = new FakeStoreLoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("password");

        when(restTemplate.postForEntity(any(String.class), any(), any(Class.class)))
                .thenThrow(new HttpClientErrorException(HttpStatusCode.valueOf(401)));

        // Act
        MultiValueMap<String, String> actualHeaders = authService.login(requestDto);

        // Assert
        assertNull(actualHeaders);
    }
}
