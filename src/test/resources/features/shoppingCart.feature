@webAutomation @shoppingCart
Feature: Shopping Cart


  Scenario: Verify the login
    Given I am logged in the website
    When I click on the woman dropdown
    And I click on the tops dropdown
    And I click on the jackets option
    And I add the first jacket to the shopping cart
    And I click Add to Cart
    Then The errors about size and color being required should be visible