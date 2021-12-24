package com.rurocker.chainvalidation.validator.rider1;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

/**
 * Age validator for rider-1.
 * Customer age must between 18-65 y.o.
 */
public class Rider1AgeValidator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider currentRider) {
        Customer customer = context.getCustomer();
        Integer age = customer.getAge();
        if(age == null || age < 18 || age > 65) {
            throw new ValidationException("Customer age for Rider-1 is between 18-65 y.o");
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    protected String getRiderCode() {
        return "rider-1";
    }
}
