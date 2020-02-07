

# ================================================================================================================
# Author: Balabharathi Jayaraman
# Functionality : As a tester, I want to be able to login using my credentials so that I can see my card details.
# ================================================================================================================

@Login
Feature: New Customer Login Feature

  Background: Open Getgo mobile application
    Given I'm on Getgo landing page

  Scenario Outline: As a tester, I want to be able to login using my credentials so that I can see my account(Virtual Card or Peso+) details
    Given I'm on Getgo login page
    When  I enter a "<username>" & click next
    And   I enter password "<password>"
    And   I click login
    Then  I should see my "<accountType>" account dashboard with my profile picture & my full name

    Examples: 
      | username         | password         | accountType |
      | Virtual_Username | Virtual_Password | Virtual     |
      | Peso_Username    | Peso_Password    | Peso        |


  Scenario: Invalid login (Invalid Email)
    Given I'm on Getgo login page
    When  I enter a "Invalid_Email" & click next
    Then System should through an invalid email error message

  Scenario: Invalid login (Invalid Password)
    Given I'm on Getgo login page
    When  I enter a "Virtual_Username" & click next
    Then System should through an invalid password message