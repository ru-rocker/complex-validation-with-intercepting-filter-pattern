package com.rurocker.chainvalidation.factory;

import com.google.common.collect.Lists;
import com.rurocker.chainvalidation.validator.IValidator;
import com.rurocker.chainvalidation.validator.baseproduct1.BaseProduct1AgeValidator;
import com.rurocker.chainvalidation.validator.baseproduct1.BaseProduct1Rider2RequiredValidator;
import com.rurocker.chainvalidation.validator.baseproduct1.BaseProduct1SumAssuredValidator;
import com.rurocker.chainvalidation.validator.rider1.Rider1AgeValidator;
import com.rurocker.chainvalidation.validator.rider2.Rider2BaseProduct1Validator;
import com.rurocker.chainvalidation.validator.rider2.Rider2SumAssuredValidator;
import com.rurocker.chainvalidation.validator.rider3.Rider3RequiredRider1Validator;
import com.rurocker.chainvalidation.validator.rider3.Rider3SumAssuredValidator;

import java.util.List;

public class ValidatorFactory {

    /**
     * Get list of validators based on product code (base product and rider code)
     *
     * @param code base product code or rider code
     * @return list of validators.
     */
    public static List<IValidator> getValidators(String code) {
        if (code == null) {
            return List.of();
        }

        // hard coded rider code is bad example, but this is only for sample.
        // use constant instead
        switch (code) {
            case "base-product-1":
                return Lists.newArrayList(new BaseProduct1AgeValidator(),
                        new BaseProduct1SumAssuredValidator(),
                        new BaseProduct1Rider2RequiredValidator());
            case "rider-1":
                return Lists.newArrayList(new Rider1AgeValidator());
            case "rider-2":
                return Lists.newArrayList(new Rider2BaseProduct1Validator(),
                        new Rider2SumAssuredValidator());
            case "rider-3":
                return Lists.newArrayList(new Rider3RequiredRider1Validator(),
                        new Rider3SumAssuredValidator());
            default:
                return Lists.newArrayList();
        }

    }
}
