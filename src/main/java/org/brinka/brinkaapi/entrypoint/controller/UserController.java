package org.brinka.brinkaapi.entrypoint.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.usecase.user.AddCardUseCase;
import org.brinka.brinkaapi.application.usecase.user.GetCardUseCase;
import org.brinka.brinkaapi.application.usecase.user.UpdateCardUseCase;
import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.entrypoint.dto.request.AddCardRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.UpdateCardRequest;
import org.brinka.brinkaapi.entrypoint.dto.response.CardResponse;
import org.brinka.brinkaapi.entrypoint.mapper.CardRequestMapper;
import org.brinka.brinkaapi.entrypoint.mapper.CardResponseMapper;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UserController {
    private final CardRequestMapper requestMapper;
    private final CardResponseMapper responseMapper;
    private final AddCardUseCase addCardUseCase;
    private final UpdateCardUseCase updateCardUseCase;
    private final GetCardUseCase getCardUseCase;

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
}
