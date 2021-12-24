package com.rurocker.chainvalidation.validator.rider1;

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

public class Rider1AgeValidatorTest {

    private final Rider1AgeValidator validator = new Rider1AgeValidator();

    @Test
    public void testValidation() {
        final String validationMesg = "Customer age for Rider-1 is between 18-65 y.o";

        Customer customer = new Customer();
        customer.setAge(18);

        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setProductCode("base-product-1");

        Rider rider = new Rider();
        rider.setRiderCode("rider-1");

        final ValidationContext context1 = new ValidationContext(baseProduct, customer,
                ImmutableMap.of("rider-1", rider));
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context1, rider));

        customer.setAge(65);
        final ValidationContext context2 = new ValidationContext(baseProduct, customer,
                ImmutableMap.of("rider-1", rider));
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context2, rider));

        customer.setAge(17);
        final ValidationContext context3 = new ValidationContext(baseProduct, customer,
                ImmutableMap.of("rider-1", rider));
        Throwable thrown = catchThrowable(() -> validator.doValidate(context3, rider));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        customer.setAge(66);
        final ValidationContext context4 = new ValidationContext(baseProduct, customer,
                ImmutableMap.of("rider-1", rider));
        thrown = catchThrowable(() -> validator.doValidate(context4, rider));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        customer.setAge(null);
        final ValidationContext context5 = new ValidationContext(baseProduct, customer,
                ImmutableMap.of("rider-1", rider));
        thrown = catchThrowable(() -> validator.doValidate(context5, rider));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);
    }
}
