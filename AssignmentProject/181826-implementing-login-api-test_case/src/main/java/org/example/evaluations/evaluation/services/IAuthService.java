package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.FakeStoreLoginRequestDto;
import org.springframework.util.MultiValueMap;

public interface IAuthService {
    MultiValueMap<String, String> login(FakeStoreLoginRequestDto fakeStoreLoginRequestDto);
}
