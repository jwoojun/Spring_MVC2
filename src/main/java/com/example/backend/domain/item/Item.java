package com.example.backend.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Item {
    private Long id;
    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 10000)
    private Integer price;

    @Range(min=10, max=100)
    private Integer quantity;

    public Item() {
    }

    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
