package com.rurocker.chainvalidation.model;

import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class Product {

    private BaseProduct baseProduct;
    private List<Rider> riders;
}
