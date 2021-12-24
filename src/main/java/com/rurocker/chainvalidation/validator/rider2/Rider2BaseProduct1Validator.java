package com.rurocker.chainvalidation.validator.rider2;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

/**
 * Validator to check that rider-2 can only be selected if the base product is base-product-1.
 */
public class Rider2BaseProduct1Validator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider currentRider) {
        String productCode = context.getBaseProduct().getProductCode();
        if(productCode == null || !productCode.equals("base-product-1")) {
            throw new ValidationException("Rider-2 specific for Base-Product-1.");
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
