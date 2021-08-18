Feature: Find Product Inventory

  Scenario: FINDING PRODUCT INVENTORY BY ID

    Given The following product inventory exists and Admin wants to find the product inventory by ID
      | inventoryId  | buyingPrice | minimumSellingPrice | batchcode   | batchdate  | quantity | reorderLevel | status | storeId | product   |
    	| 12           | 100.0       | 110.0               | AB          | null       | null     | null         | null   | null    | null      |
    	| 13           | 101.0       | 111.0               | AC          | null       | null     | null         | null   | null    | null      |
    When admin wants to find the product inventory with ID '12'
    Then the product inventory is found
