package org.brinka.brinkaapi.entrypoint.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.usecase.auth.AuthorizeLoginUseCase;
import org.brinka.brinkaapi.application.usecase.auth.SignUpUseCase;
import org.brinka.brinkaapi.entrypoint.dto.request.LoginRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.SignUpRequest;
import org.brinka.brinkaapi.entrypoint.dto.response.AuthResponse;
import org.brinka.brinkaapi.entrypoint.mapper.RequestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthorizeLoginUseCase authorizeLoginUseCase;
    private final SignUpUseCase signUpUseCase;
    private final RequestMapper requestMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        var token = authorizeLoginUseCase.execute(loginRequest.email(), loginRequest.senha());
        return ResponseEntity.ok().body(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        var token = signUpUseCase.execute(requestMapper.toInput(signUpRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }
}
