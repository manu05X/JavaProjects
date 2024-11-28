package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.FakeStoreLoginRequestDto;
import org.example.evaluations.evaluation.dtos.FakeStoreLoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthService implements IAuthService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public MultiValueMap<String, String> login(FakeStoreLoginRequestDto fakeStoreLoginRequestDto) {
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            ResponseEntity<FakeStoreLoginResponseDto> fakeStoreLoginResponseDtoResponseEntity = restTemplate.postForEntity("https://fakestoreapi.com/auth/login", fakeStoreLoginRequestDto, FakeStoreLoginResponseDto.class);
            headers.add(HttpHeaders.SET_COOKIE, fakeStoreLoginResponseDtoResponseEntity.getBody().getToken());
            return headers;
        }catch (HttpClientErrorException exception) {
            return null;
        }
    }
}
