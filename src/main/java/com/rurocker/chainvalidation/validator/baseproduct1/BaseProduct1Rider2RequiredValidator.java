package com.rurocker.chainvalidation.validator.baseproduct1;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

/**
 * Rider-2 validator for base-product-1.
 * Customer must select rider-2 if they buy base-product-1.
 */
public class BaseProduct1Rider2RequiredValidator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider currentRider) {
        Rider rider = context.getRiderMap().get("rider-2");
        if(rider == null) {
            throw new ValidationException("Base Product 1 needs Rider-2");
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
