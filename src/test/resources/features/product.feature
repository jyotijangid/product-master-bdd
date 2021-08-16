Feature: Create Product

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a product with the following attributes
      | productId  | category     | type       | brand  | productName          | image   | productDesc |
    	| 12         | Electronice  | Laptop     | Lenovo | Lenovo Ideapad 320   | null    | null        |
    When admin saves the new product 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
