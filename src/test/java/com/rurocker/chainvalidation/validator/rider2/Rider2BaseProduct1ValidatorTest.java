package com.rurocker.chainvalidation.validator.rider2;

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

public class Rider2BaseProduct1ValidatorTest {

    private final Rider2BaseProduct1Validator validator = new Rider2BaseProduct1Validator();

    @Test
    public void doValidation() {
        String validationMesg = "Rider-2 specific for Base-Product-1.";

        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setProductCode(null);

        Rider rider = new Rider();
        rider.setRiderCode("rider-2");

        final ValidationContext context1 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-2", rider));
        Throwable thrown = catchThrowable(() -> validator.doValidate(context1, rider));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        baseProduct.setProductCode("base-product-2");
        final ValidationContext context2 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-2", rider));
        thrown = catchThrowable(() -> validator.doValidate(context2, rider));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        baseProduct.setProductCode("base-product-1");
        final ValidationContext context3 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-2", rider));
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context3, rider));

    }
}
