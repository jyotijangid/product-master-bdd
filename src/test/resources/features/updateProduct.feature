Feature: Update Product

  Scenario: UPDATING THE PRODUCT NAME

    Given The following product exists and Admin wants to update the productname
      | productId  | category     | type       | brand  | productName          | image   | productDesc |
    	| 12         | Electronice  | Laptop     | Lenovo | Lenovo Ideapad 320   | null    | null        |
    When admin updates the product name to 'Lenovo Ideapad 360'
    Then the update is done