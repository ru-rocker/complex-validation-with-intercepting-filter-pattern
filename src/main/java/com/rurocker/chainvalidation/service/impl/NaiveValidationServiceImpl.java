package com.rurocker.chainvalidation.service.impl;

import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Product;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.service.ValidationService;
import com.rurocker.chainvalidation.util.Utility;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Disadvantage:
 * 1. Hard to write unit test.
 * 2. More product, more if-else statement in the class.
 * 3. New validation requirement, code refactor hell
 */
public class NaiveValidationServiceImpl implements ValidationService {

    @Override
    public void validate(Product product, Customer customer) {

        BaseProduct baseProduct = product.getBaseProduct();
        String productCode = baseProduct.getProductCode();
        List<Rider> riders = product.getRiders();

        Map<String, Rider> map = new Utility().getRiderMap(riders);
        Integer age = customer.getAge();

        // multiple-if for each base product
        if(productCode.equals("base-product-1")) {
            if(baseProduct.getSumAssured().compareTo(BigDecimal.valueOf(20000)) > 0) {
                throw new ValidationException("Base-Product-1 max sum assured is $20,000");
            }
            if(age < 18 || age > 70) {
                throw new ValidationException("Customer age for Base Product 1 is between 18-70 y.o");
            }
            if(map.get("rider-2") == null) {
                throw new ValidationException("Base Product 1 needs Rider-2");
            }
        }

        for (Rider rider : riders) {
            // multiple-if for each rider
            if("rider-2".equals(rider.getRiderCode())) {
                if(!productCode.equals("base-product-1")) {
                    throw new ValidationException("Rider-2 specific for Base-Product-1.");
                }
                if(rider.getSumAssured().compareTo(BigDecimal.valueOf(10000)) > 0) {
                    throw new ValidationException("Rider-2 max sum assured is $10,000");
                }
            }
            else if("rider-1".equals(rider.getRiderCode())) {
                if(age < 18 || age > 65) {
                    throw new ValidationException("Customer age for Rider-1 is between 18-65 y.o");
                }
            }
            else if("rider-3".equals(rider.getRiderCode())) {
                if(map.get("rider-1") == null) {
                    throw new ValidationException("Rider-1 needs to be selected as a pre-req for Rider-3.");
                }
                if(rider.getSumAssured().compareTo(BigDecimal.valueOf(5000)) > 0) {
                    throw new ValidationException("Rider-3 max sum assured is $5,000");
                }
            }
        }

    }
}
