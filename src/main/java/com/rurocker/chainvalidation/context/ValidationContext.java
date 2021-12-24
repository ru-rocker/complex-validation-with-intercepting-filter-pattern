package com.rurocker.chainvalidation.context;

import com.google.common.collect.ImmutableMap;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Product;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.model.Customer;
import lombok.*;

/**
 * Validation context is needed to hold the important values and pass it between validation chain.
 * Consider this as tuple, because Java lack of Tuple :)
 */
@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
public class ValidationContext {

    private BaseProduct baseProduct;
    private Customer customer;
    private ImmutableMap<String, Rider> riderMap;

}
