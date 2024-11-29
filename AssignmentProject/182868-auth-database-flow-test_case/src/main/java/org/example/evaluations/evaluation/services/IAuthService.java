package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.LoginRequest;
import org.example.evaluations.evaluation.dtos.LoginResponse;
import org.example.evaluations.evaluation.dtos.SignupRequest;
import org.example.evaluations.evaluation.dtos.SignupResponse;
import org.example.evaluations.evaluation.exceptions.BadCredentialsException;
import org.example.evaluations.evaluation.exceptions.UserNotSignedUpException;

public interface IAuthService {
    SignupResponse signup(SignupRequest request);

    LoginResponse login(LoginRequest loginRequest) throws UserNotSignedUpException, BadCredentialsException;
}
