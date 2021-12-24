package com.rurocker.chainvalidation.validator.rider3;

import com.google.common.collect.ImmutableMap;
import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Rider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class Rider3SumAssuredValidatorTest {

    private final Rider3SumAssuredValidator validator = new Rider3SumAssuredValidator();

    @Test
    public void doValidation() {
        String validationMesg = "Rider-3 max sum assured is $5,000";

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

        rider3.setSumAssured(BigDecimal.ZERO);
        final ValidationContext context2 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-3", rider3));
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context2, rider3));

        rider3.setSumAssured(BigDecimal.valueOf(5000));
        final ValidationContext context3 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-3", rider3));
        Assertions.assertDoesNotThrow(() -> validator.doValidate(context3, rider3));

        rider3.setSumAssured(BigDecimal.valueOf(5000.50));
        final ValidationContext context4 = new ValidationContext(baseProduct, new Customer(),
                ImmutableMap.of("rider-3", rider3));
        thrown = catchThrowable(() -> validator.doValidate(context4, rider3));
        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining(validationMesg);
    }
}
