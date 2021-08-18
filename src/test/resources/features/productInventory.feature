Feature: Create ProductInventory

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a product inventory with the following attributes
      | inventoryId  | buyingPrice | minimumSellingPrice | batchcode   | batchdate  | quantity | reorderLevel | status | storeId | product   |
    	| 12           | 100.0       | 110.0               | AB          | null       | null     | null         | null   | null    | null      |
    #And with the following details of product
      #| productId  | category     | type       | brand  | productName          | image   | productDesc |
    #	| 12         | Electronics  | Laptop     | Lenovo | Lenovo Ideapad 320   | null    | null        |
    When admin saves the new product inventory 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL' 
