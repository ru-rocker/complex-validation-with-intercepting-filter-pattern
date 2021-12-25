package com.rurocker.chainvalidation.validator;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.model.Rider;

import java.util.Objects;

/**
 * Abstract class as a wrapper for validator
 */
public abstract class AbstractValidator implements IValidator{

    @Override
    public void validate(ValidationContext context) {
        if(isActive()) {
            Rider rider = context.getRiderMap().get(Objects.
                    requireNonNullElse(getRiderCode(), ""));
            doValidate(context, rider);
        }
    }

    protected abstract void doValidate(ValidationContext context, Rider currentRider);

    /**
     * Get rider code, null if this is base product
     * @return rider code, or null if the validator belongs to base product.
     */
    protected abstract String getRiderCode();
}
