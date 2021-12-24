package com.rurocker.chainvalidation.validator.baseproduct1;

import com.google.common.collect.ImmutableMap;
import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Product;
import com.rurocker.chainvalidation.model.Rider;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class BaseProduct1AgeValidatorTest {

    private final BaseProduct1AgeValidator validator = new BaseProduct1AgeValidator();

    @Test
    public void testValidation() {

        final String validationMesg = "Customer age for Base Product 1 is between 18-70 y.o";

        Customer customer = new Customer();
        customer.setAge(18);

        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setProductCode("base-product-1");

        final ValidationContext context1 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context1, null));

        customer.setAge(70);
        final ValidationContext context2 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context2, null));

        customer.setAge(71);
        final ValidationContext context3 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        Throwable thrown = catchThrowable(() -> validator.doValidate(context3, null));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        customer.setAge(17);
        final ValidationContext context4 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        thrown = catchThrowable(() -> validator.doValidate(context4, null));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        customer.setAge(null);
        final ValidationContext context5 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        thrown = catchThrowable(() -> validator.doValidate(context5, null));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);
    }

}
