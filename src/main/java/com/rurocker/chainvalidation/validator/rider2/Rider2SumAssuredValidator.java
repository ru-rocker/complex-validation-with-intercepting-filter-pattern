package com.rurocker.chainvalidation.validator.rider2;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

import java.math.BigDecimal;

/**
 * Sum assured validator for rider-2.
 * Max sum assured is $10,000
 */
public class Rider2SumAssuredValidator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider currentRider) {
        BigDecimal sumAssured = currentRider.getSumAssured();
        if(sumAssured == null || sumAssured.compareTo(BigDecimal.valueOf(10000)) > 0) {
            throw new ValidationException("Rider-2 max sum assured is $10,000");
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    protected String getRiderCode() {
        return "rider-2";
    }
}
