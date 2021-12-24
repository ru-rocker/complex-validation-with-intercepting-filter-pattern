package com.rurocker.chainvalidation.validator.baseproduct1;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

import java.math.BigDecimal;

/**
 * Sum assured validator for base-product-1.
 * Max sum assured is $20,000
 */
public class BaseProduct1SumAssuredValidator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider currentRider) {
        BaseProduct baseProduct = context.getBaseProduct();
        BigDecimal sumAssured = baseProduct.getSumAssured();
        if(sumAssured == null || sumAssured.compareTo(BigDecimal.valueOf(20000)) > 0) {
            throw new ValidationException("Base-Product-1 max sum assured is $20,000");
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    protected String getRiderCode() {
        return null;
    }
}
