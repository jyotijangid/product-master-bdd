Feature: Update Product Inventory

  Scenario: UPDATING THE PRODUCT BATCHCODE

    Given The following product inventory exists and Admin wants to update the batchcode
      | inventoryId  | buyingPrice | minimumSellingPrice | batchcode   | batchdate  | quantity | reorderLevel | status | storeId | product   |
    	| 12           | 100.0       | 110.0               | AB          | null       | null     | null         | null   | null    | null      |
    When admin updates the batchcode to 'AD'
    Then the update is done
