package org.brinka.brinkaapi.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.brinka.brinkaapi.domain.exception.CartItemNotFoundException;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Cart {
    private Integer userId;
    private List<CartItem> items;

    public void removeItem(Integer productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public void addItem(Integer productId) {
        if (containsProduct(productId)) {
            return;
        }

        items.add(CartItem.builder()
                .productId(productId)
                .quantity(1)
                .build());
    }

    public void incrementItem(Integer productId) {
        findItem(productId).increment();
    }

    public void decrementItem(Integer productId) {
        var item = findItem(productId);

        item.decrement();

        if (item.getQuantity() <= 0) {
            removeItem(productId);
        }
    }

    private boolean containsProduct(Integer productId) {
        return items.stream()
                .anyMatch(item -> item.getProductId().equals(productId));
    }

    private CartItem findItem(Integer productId) throws CartItemNotFoundException {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException(productId));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
