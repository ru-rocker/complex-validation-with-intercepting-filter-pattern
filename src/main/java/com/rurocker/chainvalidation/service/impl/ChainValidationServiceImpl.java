package com.rurocker.chainvalidation.service.impl;

import com.google.common.collect.ImmutableMap;
import com.rurocker.chainvalidation.context.ValidationContext;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Product;
import com.rurocker.chainvalidation.service.ValidationService;
import com.rurocker.chainvalidation.util.Utility;
import com.rurocker.chainvalidation.validator.ValidatorChain;

/**
 * Implementation using interceptor filter-chain design pattern.
 * This class behaves like a manager.
 * https://en.wikipedia.org/wiki/Intercepting_filter_pattern.
 */
public class ChainValidationServiceImpl implements ValidationService {

    @Override
    public void validate(Product product, Customer customer) {
        Utility utility = new Utility();
        ValidatorChain validatorChain = new ValidatorChain();
        ValidationContext context = new ValidationContext(product.getBaseProduct(), customer,
                ImmutableMap.copyOf(utility.getRiderMap(product.getRiders())));
        validatorChain.execute(context, utility.getAllValidators(product));
    }
}
