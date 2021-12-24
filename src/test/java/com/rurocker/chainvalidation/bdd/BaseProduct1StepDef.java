package com.rurocker.chainvalidation.bdd;

import com.rurocker.chainvalidation.exception.ValidationException;
import com.rurocker.chainvalidation.model.BaseProduct;
import com.rurocker.chainvalidation.model.Customer;
import com.rurocker.chainvalidation.model.Product;
import com.rurocker.chainvalidation.model.Rider;
import com.rurocker.chainvalidation.service.ValidationService;
import com.rurocker.chainvalidation.service.impl.ChainValidationServiceImpl;
import com.rurocker.chainvalidation.service.impl.NaiveValidationServiceImpl;
import io.cucumber.java8.En;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/base-product-1.feature"},
        glue = {"com.rurocker.chainvalidation.bdd"},
        plugin = {"pretty"})
public class BaseProduct1StepDef implements En {

    private final ValidationService naiveValidationService = new NaiveValidationServiceImpl();
    private final ValidationService chainValidationService = new ChainValidationServiceImpl();

    public BaseProduct1StepDef() {
        List<Rider> riders = new ArrayList<>();
        Product product = new Product();
        BaseProduct baseProduct = new BaseProduct();
        Customer customer = new Customer();
        Given("User with age ${int} selected {string} with sum assured ${double}",
                (Integer age, String productCode, Double sa) -> {
                    customer.setAge(age);
                    baseProduct.setSumAssured(BigDecimal.valueOf(sa));
                    baseProduct.setProductCode(productCode);
                });
        When("User selected {string} with sum assured ${double}",
                (String riderCode, Double sa) -> {
                    if (riderCode != null && !riderCode.isEmpty()) {
                        Rider rider = new Rider();
                        rider.setRiderCode(riderCode);
                        rider.setSumAssured (BigDecimal.valueOf(sa));
                        riders.add(rider);
                    }
                });
        Then("^System will return a valid flag \"([^\"]*)\" to determine customer can buy the product$",
                (String flag) -> {
                    String res = "Y";
                    product.setBaseProduct(baseProduct);
                    product.setRiders(riders);
                    try {
                        // test both service
                        naiveValidationService.validate(product, customer);
                        chainValidationService.validate(product, customer);
                    } catch (ValidationException e) {
                        res = "N";
                    }
                    assertThat(res).isEqualTo(flag);
                });
    }

}
