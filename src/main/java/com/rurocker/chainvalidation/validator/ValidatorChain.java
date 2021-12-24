package com.rurocker.chainvalidation.validator;

import com.rurocker.chainvalidation.context.ValidationContext;

import java.util.List;

/**
 * Class to handle validation execution in chain style
 */
public class ValidatorChain {

    public void execute(ValidationContext context, List<IValidator> validators) {
        for (IValidator validator : validators) {
            validator.validate(context);
        }
    }
}
