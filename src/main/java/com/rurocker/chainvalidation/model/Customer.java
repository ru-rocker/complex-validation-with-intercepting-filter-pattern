package com.rurocker.chainvalidation.model;

import lombok.*;

@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class Customer {

    private String name;
    private Integer age;

    public static class Builder {
    }

}
