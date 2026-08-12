package org.brinka.brinkaapi.application.usecase.order;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.enums.PaymentMethod;
import org.brinka.brinkaapi.domain.exception.*;
import org.brinka.brinkaapi.domain.model.*;
import org.brinka.brinkaapi.domain.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CreateOrderUseCase {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    @Transactional
    public Order execute(PaymentMethod metodoPagamento, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        addressRepository.findAddressByUser(user)
                .orElseThrow(() -> new AddressNotFoundException(user.getEmail()));

        if (metodoPagamento == PaymentMethod.CARTAO_CREDITO) {
            cardRepository.findCardByUser(user)
                    .orElseThrow(() -> new CardNotFoundException(user.getEmail()));
        }

        var cart = cartRepository.getCartByUserId(user.getId());

        if (cart.isEmpty()) {
            throw new EmptyCartException();
        }

        var productIds = cart.getItems().stream()
                .map(CartItem::getProductId)
                .toList();

        var productsById = productRepository.findProductsById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        var itens = new ArrayList<OrderItem>();

        for (var cartItem : cart.getItems()) {
            var product = productsById.get(cartItem.getProductId());

            if (product == null) {
                throw new ProductNotFoundException(cartItem.getProductId());
            }

            if (product.getEstoque() == null || product.getEstoque() < cartItem.getQuantity()) {
                throw new InsufficientStockException(product.getId());
            }

            itens.add(OrderItem.builder()
                    .produto(product)
                    .quantidade(cartItem.getQuantity())
                    .precoUnitario(product.getPreco())
                    .build());
        }

        var subtotal = itens.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var total = subtotal;

        var pedidoStatus = orderStatusRepository.findByDescricao("PAGO")
                .orElseThrow(() -> new IllegalStateException("Status de pedido 'PAGO' não configurado"));
        var pagamentoStatus = paymentStatusRepository.findByDescricao("APROVADO")
                .orElseThrow(() -> new IllegalStateException("Status de pagamento 'APROVADO' não configurado"));
        var tipoPagamento = paymentTypeRepository.findByDescricao(metodoPagamento.name())
                .orElseThrow(() -> new IllegalStateException("Tipo de pagamento '" + metodoPagamento.name() + "' não configurado"));

        var pagamento = Payment.builder()
                .tipo(tipoPagamento)
                .status(pagamentoStatus)
                .valor(total)
                .dataPagamento(LocalDateTime.now())
                .build();

        var order = Order.builder()
                .usuario(user)
                .status(pedidoStatus)
                .itens(itens)
                .pagamento(pagamento)
                .subtotal(subtotal)
                .desconto(BigDecimal.ZERO)
                .frete(BigDecimal.ZERO)
                .total(total)
                .dataPedido(LocalDateTime.now())
                .build();

        var savedOrder = orderRepository.save(order);

        itens.forEach(item -> {
            var product = item.getProduto();
            product.setEstoque(product.getEstoque() - item.getQuantidade());
            productRepository.saveProduct(product);
        });

        cartRepository.saveCart(Cart.builder()
                .userId(user.getId())
                .items(new ArrayList<>())
                .build());

        return savedOrder;
    }
}
