@webAutomation @login
Feature: Login feature


  Scenario: Verify the login
    Given I am in the home page
    When I click in sing in
    When I send the login information
    Then The welcome user text should be visible