package com.rurocker.chainvalidation.factory;

import com.rurocker.chainvalidation.validator.baseproduct1.BaseProduct1AgeValidator;
import com.rurocker.chainvalidation.validator.baseproduct1.BaseProduct1Rider2RequiredValidator;
import com.rurocker.chainvalidation.validator.baseproduct1.BaseProduct1SumAssuredValidator;
import com.rurocker.chainvalidation.validator.rider1.Rider1AgeValidator;
import com.rurocker.chainvalidation.validator.rider2.Rider2BaseProduct1Validator;
import com.rurocker.chainvalidation.validator.rider2.Rider2SumAssuredValidator;
import com.rurocker.chainvalidation.validator.rider3.Rider3RequiredRider1Validator;
import com.rurocker.chainvalidation.validator.rider3.Rider3SumAssuredValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorFactoryTest {

    @Test
    public void testValidatorFactory(){

        String code = null;
        assertThat(ValidatorFactory.getValidators(code)).isEmpty();

        code = "not-exists";
        assertThat(ValidatorFactory.getValidators(code)).isEmpty();

        code = "base-product-1";
        assertThat(ValidatorFactory.getValidators(code))
                .anyMatch(item -> item instanceof BaseProduct1AgeValidator)
                .anyMatch(item -> item instanceof BaseProduct1SumAssuredValidator)
                .anyMatch(item -> item instanceof BaseProduct1Rider2RequiredValidator);

        code = "rider-1";
        assertThat(ValidatorFactory.getValidators(code))
                .anyMatch(item -> item instanceof Rider1AgeValidator);

        code = "rider-2";
        assertThat(ValidatorFactory.getValidators(code))
                .anyMatch(item -> item instanceof Rider2BaseProduct1Validator)
                .anyMatch(item -> item instanceof Rider2SumAssuredValidator);

        code = "rider-3";
        assertThat(ValidatorFactory.getValidators(code))
                .anyMatch(item -> item instanceof Rider3RequiredRider1Validator)
                .anyMatch(item -> item instanceof Rider3SumAssuredValidator);
    }
}
