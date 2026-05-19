@webAutomation @shoppingCart
Feature: Shopping Cart

  Background:
    Given I am already logged in

  @shoppingCart1
  Scenario: Successfully complete a purchase
    Given I am in the home page
    When I search for "combination pliers" in the search bar
    And All the products should be "Combination Pliers"
    * I click on the product
    * I click Add to Cart
    * I click on the shopping cart icon
    * The item, quantity, price and total should be visible
    * I click proceed to checkout
    * In the sing in step I click in the second proceed to checkout
    * in the billing address step I click in the third proceed to checkout
    * in the payment select I choose credit card
    * I fill the credit card information
    Then the successful payment message should be displayed

  @shoppingCart2
  Scenario: Delete a product from the shopping cart
    Given I am in the home page
    When I search for "combination pliers" in the search bar
    And All the products should be "Combination Pliers"
    * I click on the product
    * I click Add to Cart
    * I click on the shopping cart icon
    * The item, quantity, price and total should be visible
    And I click in the button to delete a product
    Then The item, quantity, price and total should not be visible



