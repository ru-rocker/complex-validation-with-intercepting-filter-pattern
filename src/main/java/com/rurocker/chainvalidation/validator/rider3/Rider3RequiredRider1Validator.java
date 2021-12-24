package com.rurocker.chainvalidation.validator.rider3;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

/**
 * Customer must buy rider-1 in order to buy rider-3.
 */
public class Rider3RequiredRider1Validator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider currentRider) {
        Rider rider = context.getRiderMap().get("rider-1");
        if(rider == null) {
            throw new ValidationException("Rider-1 needs to be selected as a pre-req for Rider-3.");
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
