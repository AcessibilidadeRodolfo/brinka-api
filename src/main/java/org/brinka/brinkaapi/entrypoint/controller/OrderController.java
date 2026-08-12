package org.brinka.brinkaapi.entrypoint.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.usecase.order.CreateOrderUseCase;
import org.brinka.brinkaapi.application.usecase.order.GetOrderByIdUseCase;
import org.brinka.brinkaapi.application.usecase.order.GetOrdersUseCase;
import org.brinka.brinkaapi.entrypoint.dto.request.CreateOrderRequest;
import org.brinka.brinkaapi.entrypoint.dto.response.OrderResponse;
import org.brinka.brinkaapi.entrypoint.mapper.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrdersUseCase getOrdersUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final ResponseMapper responseMapper;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request, Authentication authentication) {
        String email = authentication.getName();

        var order = createOrderUseCase.execute(request.metodoPagamento(), email);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toResponse(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toOrderResponseList(getOrdersUseCase.execute(email)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id, Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(responseMapper.toResponse(getOrderByIdUseCase.execute(id, email)));
    }
}
