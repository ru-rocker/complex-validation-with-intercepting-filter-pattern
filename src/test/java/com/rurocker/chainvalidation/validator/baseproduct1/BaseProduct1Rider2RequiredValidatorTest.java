package com.rurocker.chainvalidation.validator.baseproduct1;

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

public class BaseProduct1Rider2RequiredValidatorTest {

    private final BaseProduct1Rider2RequiredValidator validator = new BaseProduct1Rider2RequiredValidator();

    @Test
    public void testValidation() {
        final String validationMesg = "Base Product 1 needs Rider-2";

        Customer customer = new Customer();
        customer.setAge(18);

        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setProductCode("base-product-1");

        final ValidationContext context1 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        Throwable thrown = catchThrowable(() -> validator.doValidate(context1, null));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        Rider rider1 = new Rider();
        rider1.setRiderCode("rider-1");

        final ValidationContext context2 = new ValidationContext(baseProduct, customer, ImmutableMap.of("rider-1", rider1));
        thrown = catchThrowable(() -> validator.doValidate(context2, null));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        Rider rider2 = new Rider();
        rider2.setRiderCode("rider-2");

        final ValidationContext context3 = new ValidationContext(baseProduct, customer,
                ImmutableMap.of("rider-1", rider1, "rider-2", rider2));
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context3, null));
    }

}
