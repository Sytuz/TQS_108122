@hwBuyTicket
Feature: Buying a Ticket
 
    Background: Select a trip
        Given I have selected a trip

    Scenario: Show ticket information on valid payment information
        Given I click on the buy button
        When I enter valid payment information
        And I click on the submit button
        Then I should see the ticket information

    Scenario: Show error message on invalid payment information (empty fields)
        Given I click on the buy button
        When I leave the payment information fields empty
        And I click on the submit button
        Then I should see an error message