package com.rurocker.chainvalidation.validator;

import com.rurocker.chainvalidation.context.ValidationContext;

/**
 * Interface for product validations.
 */
public interface IValidator {

    /**
     * Validate the product (base product and riders)
     * @param context validation context
     */
    void validate(ValidationContext context);

    /**
     * Determine whether the validator is active or not.
     * @return true if active. Otherwise false.
     */
    boolean isActive();
}
