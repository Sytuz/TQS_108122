@hwMakeReservation
Feature: Making a reservation
 
    Background: Select a trip
        Given I have selected a trip

    Scenario: Show confirmation on payment
        Given I click on the buy button
        When I enter valid payment information
        And I click on the submit button
        Then I should see a confirmation message