Feature: Product-1 validation features

  @Product1_Validation
  Scenario Outline: Users wants to buy Product-1
    Given User with age $<age> selected "<baseProduct>" with sum assured $<sumAssured0>
    When User selected "<rider1>" with sum assured $<sumAssured1>
     And User selected "<rider2>" with sum assured $<sumAssured2>
     And User selected "<rider3>" with sum assured $<sumAssured3>
    Then System will return a valid flag "<flag>" to determine customer can buy the product
    Examples:
    |age|baseProduct   |sumAssured0|rider1 |sumAssured1|rider2 |sumAssured2|rider3 |sumAssured3|flag|
    |17 |base-product-1|0          |rider-2|0          |       |0          |       |0          |N   |
    |71 |base-product-1|0          |rider-2|0          |       |0          |       |0          |N   |
    |30 |base-product-1|25000      |rider-2|0          |       |0          |       |0          |N   |
    |30 |base-product-1|15000      |rider-2|10500      |       |0          |       |0          |N   |
    |30 |base-product-1|15000      |       |0          |       |0          |       |0          |N   |
    |30 |base-product-1|15000      |rider-2|5500       |       |0          |       |0          |Y   |
    |30 |base-product-2|15000      |rider-2|5500       |       |0          |       |0          |N   |
    |68 |base-product-1|15000      |rider-1|10500      |       |0          |       |0          |N   |
    |30 |base-product-1|15000      |rider-1|10500      |       |0          |       |0          |N   |
    |30 |base-product-2|15000      |rider-1|10500      |       |0          |       |0          |Y   |
    |30 |base-product-1|15000      |rider-3|0          |       |0          |       |0          |N   |
    |30 |base-product-2|15000      |rider-1|5000       |rider-3|5500       |       |0          |N   |
    |30 |base-product-2|15000      |rider-1|5000       |rider-3|5000       |       |0          |Y   |
    |30 |base-product-1|15000      |rider-2|5000       |rider-1|5500       |rider-3|5500       |N   |
    |30 |base-product-1|15000      |rider-2|5000       |rider-1|5000       |rider-3|5000       |Y   |
    |68 |base-product-1|15000      |rider-2|10000      |rider-1|0          |       |0          |N   |
    |40 |base-product-1|15000      |rider-2|10000      |rider-3|500        |       |0          |N   |
