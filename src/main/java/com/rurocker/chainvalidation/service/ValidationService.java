package com.rurocker.chainvalidation.service;

import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Product;

public interface ValidationService {

    void validate(Product product, Customer customer);
}
