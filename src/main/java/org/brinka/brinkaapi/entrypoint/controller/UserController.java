package org.brinka.brinkaapi.entrypoint.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.usecase.user.*;
import org.brinka.brinkaapi.entrypoint.dto.request.AddCardRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.UpdateCardRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.UpdateEnderecoRequest;
import org.brinka.brinkaapi.entrypoint.dto.response.CardResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.EnderecoResponse;
import org.brinka.brinkaapi.entrypoint.mapper.RequestMapper;
import org.brinka.brinkaapi.entrypoint.mapper.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UserController {
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final AddCardUseCase addCardUseCase;
    private final UpdateCardUseCase updateCardUseCase;
    private final GetCardUseCase getCardUseCase;
    private final GetEnderecoUseCase getEnderecoUseCase;
    private final UpdateEnderecoUseCase updateEnderecoUseCase;

    @GetMapping("/cartao")
    public ResponseEntity<CardResponse> getCard(Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(getCardUseCase.execute(email)));
    }

    @PostMapping("/cartao")
    public ResponseEntity<CardResponse> addCard(@RequestBody @Valid AddCardRequest request, Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toResponse(addCardUseCase.execute(requestMapper.toInput(request), email)));
    }

    @PatchMapping("/cartao")
    public ResponseEntity<CardResponse> updateCard(@RequestBody @Valid UpdateCardRequest request, Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(updateCardUseCase.execute(requestMapper.toInput(request), email)));
    }

    @GetMapping("/endereco")
    public ResponseEntity<EnderecoResponse> getEndereco(Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(getEnderecoUseCase.execute(email)));
    }

    @PatchMapping("/endereco")
    public ResponseEntity<EnderecoResponse> updateEndereco(UpdateEnderecoRequest request, Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(updateEnderecoUseCase.execute(requestMapper.toInput(request), email)));
    }
}
