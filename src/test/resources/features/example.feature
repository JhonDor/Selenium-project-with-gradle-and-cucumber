@webAutomation
Feature: Example feature




  @myTag
  Scenario: : testing home page
    Given I am in the home page
    When I click in sing in
    When I send the login info
    Then The welcome user text should be visible