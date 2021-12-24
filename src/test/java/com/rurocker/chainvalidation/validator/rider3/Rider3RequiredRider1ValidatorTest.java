package com.rurocker.chainvalidation.validator.rider3;

import com.google.common.collect.ImmutableMap;
import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Rider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class Rider3RequiredRider1ValidatorTest {

    private final Rider3RequiredRider1Validator validator = new Rider3RequiredRider1Validator();

    @Test
    public void doValidation() {
        String validationMesg = "Rider-1 needs to be selected as a pre-req for Rider-3.";

        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setProductCode("base-product-1");

        Rider rider3 = new Rider();
        rider3.setRiderCode("rider-3");

        final ValidationContext context1 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-3", rider3));
        Throwable thrown = catchThrowable(() -> validator.doValidate(context1, rider3));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        Rider rider1 = new Rider();
        rider1.setRiderCode("rider-1");
        final ValidationContext context2 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-3", rider3, "rider-1", rider1));
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context2, rider3));

    }
}
