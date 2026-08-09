package org.brinka.brinkaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class CartItem {

    private Integer productId;
    private Integer quantity;

    public void increment() {
        this.quantity++;
    }

    public void decrement() {
        this.quantity--;
    }

}