package com.rurocker.chainvalidation.util;

import com.rurocker.chainvalidation.factory.ValidatorFactory;
import com.rurocker.chainvalidation.model.Product;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.validator.IValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class
 */
public class Utility {

    public Map<String, Rider> getRiderMap(List<Rider> riders) {
        Map<String, Rider> map = new HashMap<>();
        for (Rider rider : riders) {
            map.put(rider.getRiderCode(), rider);
        }
        return map;
    }

    public List<IValidator> getAllValidators(Product product) {
        List<IValidator> validators = ValidatorFactory.
                getValidators(product.getBaseProduct().getProductCode());
        List<Rider> riders = product.getRiders();
        for (Rider rider : riders) {
            validators.addAll(ValidatorFactory.getValidators(rider.getRiderCode()));
        }
        return validators;
    }
}
