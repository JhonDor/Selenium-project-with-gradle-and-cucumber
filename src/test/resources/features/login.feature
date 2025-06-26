@webAutomation @login
Feature: Login feature


  Scenario: Verify the login
    Given I am in the home page
    When I click in sing in
    When I send the login information
    Then The My account should be visible