@webAutomation @homepage
Feature: Example feature


  Scenario: Verify the hot sellers section is properly displayed
    Given I am in the home page
    When I scroll to the hot sellers section
    Then the hot sellers section should have 6 products


  Scenario: Verify the hot sellers items have all their properties properly displayed
    Given I am in the home page
    When I scroll to the hot sellers section
    Then the hot sellers items should have all their information displayed