package com.rurocker.chainvalidation.validator.baseproduct1;

import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.AbstractValidator;

/**
 * Age validator for base-product-1.
 * Customer age must between 18-70 y.o.
 */
public class BaseProduct1AgeValidator extends AbstractValidator {

    @Override
    protected void doValidate(ValidationContext context, Rider current) {
        Customer customer = context.getCustomer();
        Integer age = customer.getAge();
        if(age == null || age < 18 || age > 70) {
            throw new ValidationException("Customer age for Base Product 1 is between 18-70 y.o");
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
