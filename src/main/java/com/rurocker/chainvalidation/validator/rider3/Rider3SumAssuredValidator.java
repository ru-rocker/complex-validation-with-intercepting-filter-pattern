package com.rurocker.chainvalidation.validator.rider3;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

import java.math.BigDecimal;

/**
 * Sum assured validator for rider-3.
 * Max sum assured is $5,000
 */
public class Rider3SumAssuredValidator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider currentRider) {
        BigDecimal sumAssured = currentRider.getSumAssured();
        if(sumAssured == null || sumAssured.compareTo(BigDecimal.valueOf(5000)) > 0) {
            throw new ValidationException("Rider-3 max sum assured is $5,000");
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    protected String getRiderCode() {
        return "rider-3";
    }
}
