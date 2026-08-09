package org.brinka.brinkaapi.entrypoint.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.usecase.user.card.AddCardUseCase;
import org.brinka.brinkaapi.application.usecase.user.card.GetCardUseCase;
import org.brinka.brinkaapi.application.usecase.user.card.UpdateCardUseCase;
import org.brinka.brinkaapi.application.usecase.user.cart.AddCartItemUseCase;
import org.brinka.brinkaapi.application.usecase.user.cart.GetCartUseCase;
import org.brinka.brinkaapi.application.usecase.user.cart.RemoveCartItemUseCase;
import org.brinka.brinkaapi.application.usecase.user.cart.UpdateCartItemUseCase;
import org.brinka.brinkaapi.application.usecase.user.endereco.GetAddressUseCase;
import org.brinka.brinkaapi.application.usecase.user.endereco.UpdateAddressUseCase;
import org.brinka.brinkaapi.domain.enums.CartOperation;
import org.brinka.brinkaapi.entrypoint.dto.request.AddCardRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.UpdateCardRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.UpdateAddressRequest;
import org.brinka.brinkaapi.entrypoint.dto.response.CardResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.AddressResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.CartResponse;
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
    private final GetAddressUseCase getAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final AddCartItemUseCase addCartItemUseCase;
    private final GetCartUseCase getCartUseCase;
    private final RemoveCartItemUseCase removeCartItemUseCase;
    private final UpdateCartItemUseCase updateCartItemUseCase;

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

    @GetMapping("/address")
    public ResponseEntity<AddressResponse> getAddress(Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(getAddressUseCase.execute(email)));
    }

    @PatchMapping("/address")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody @Valid UpdateAddressRequest request, Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(updateAddressUseCase.execute(requestMapper.toInput(request), email)));
    }

    @GetMapping("/carrinho")
    public ResponseEntity<CartResponse> getCart(Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(getCartUseCase.execute(email)));
    }

    @PostMapping("/carrinho")
    public ResponseEntity<CartResponse> addCartItem(@RequestParam Integer productId, Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(addCartItemUseCase.execute(productId, email)));
    }

    @PatchMapping("/carrinho/{productId}")
    public ResponseEntity<CartResponse> updateCartItem(@RequestParam CartOperation operation, @PathVariable Integer productId, Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(updateCartItemUseCase.execute(productId, operation, email)));
    }

    @DeleteMapping("/carrinho/{productId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Integer productId, Authentication authentication) {
        String email = authentication.getName();

        removeCartItemUseCase.execute(productId, email);

        return ResponseEntity.noContent().build();
    }
}
