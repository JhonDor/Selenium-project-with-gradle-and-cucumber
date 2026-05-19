@webAutomation @homepage
Feature: Verify the elements of the front page

   @homePage1
   Scenario: Verify products are properly displayed in the home page
     Given I am in the home page
     Then The home page should have a banner
     And The sort option should be displayed
     Then 9 products should be displayed
     And Price Range should be displayed
     And Filters should be displayed

  @homePage2
  Scenario: Verify the search bar is working
    Given I am in the home page
    When I search for "pliers" in the search bar
    Then All the products should be "Pliers"


  @homePage3
  Scenario: Verify the sorting in alphabetical order is working
    Given I am in the home page
    When I sort the products by alphabetical order
    Then All products should be in alphabetical order



