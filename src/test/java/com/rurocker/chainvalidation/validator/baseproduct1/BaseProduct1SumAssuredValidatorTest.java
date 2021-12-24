package com.rurocker.chainvalidation.validator.baseproduct1;

import com.google.common.collect.ImmutableMap;
import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class BaseProduct1SumAssuredValidatorTest {

    private final BaseProduct1SumAssuredValidator validator = new BaseProduct1SumAssuredValidator();

    @Test
    public void testValidation() {
        final String validationMesg = "Base-Product-1 max sum assured is $20,000";

        Customer customer = new Customer();
        customer.setAge(18);

        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setProductCode("base-product-1");
        baseProduct.setSumAssured(null);

        final ValidationContext context1 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        Throwable thrown = catchThrowable(() -> validator.doValidate(context1, null));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        baseProduct.setSumAssured(BigDecimal.valueOf(20001));
        final ValidationContext context2 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        thrown = catchThrowable(() -> validator.doValidate(context2, null));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);

        baseProduct.setSumAssured(BigDecimal.valueOf(0));
        final ValidationContext context3 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context3, null));

        baseProduct.setSumAssured(BigDecimal.valueOf(20000));
        final ValidationContext context4 = new ValidationContext(baseProduct, customer, ImmutableMap.of());
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context4, null));

    }
}
