@webAutomation
Feature: Product page

  Scenario:
    Given I am in the home page
    When I search for "combination pliers" in the search bar
    And All the products should be "Combination Pliers"
    And I click on the product
    Then The title, product image, description, price, input quantity and add to favourites should be visible


  Scenario:
    Given I am in the home page
    When I search for "combination pliers" in the search bar
    And All the products should be "Combination Pliers"
    And I click on the product
    And I click Add to Cart
    Then The pop-up showing the product was added to the shopping cart should be visible



