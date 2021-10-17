package com.example.thymeleaf.domain.item;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class ItemTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setItemName("ProductA");
        item.setPrice(1000000);
        item.setQuantity(0);;

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        for(ConstraintViolation<Item> violation : violations){
            System.out.println(violation);
            System.out.println(violation.getMessage());
        }
    }

}