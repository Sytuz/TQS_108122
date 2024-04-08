@hwMakeReservation
Feature: Making a reservation
 
    Background: Select a trip
        Given I have selected a trip

    Scenario: Show reservation information on valid payment information
        Given I click on the buy button
        When I enter valid payment information
        And I click on the submit button
        Then I should see the reservation information

    Scenario: Show error message on invalid payment information (empty fields)
        Given I click on the buy button
        When I leave the payment information fields empty
        And I click on the submit button
        Then I should see an error message