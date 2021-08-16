Feature: Find Product

  Scenario: FINDING PRODUCT BY ID

    Given The following product exists and Admin wants to find the product by ID
		  | productId  | category     | type       | brand     | productName          | image   | productDesc |
    	| 12         | Electronics  | Laptop     | Lenovo    | Lenovo Ideapad 320   | null    | null        |
   		| 13         | Electronics  | Laptop     | Lenovo    | Lenovo Ideapad 360   | null    | null        |	
    When admin wants to find the product with ID '12'
    Then the product is found