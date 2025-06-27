@webAutomation @login
Feature: Login feature


  Scenario: Register a new user
    Given I am in the home page
    When I click in sing in
    When I click the register link
    And I fill the registration form
    And I send the login information
    Then The My account should be visible

