@hwTests
Feature: Frontend tests

    Background:
        Given I am on the homepage

    Scenario: Check the reservation information
        When I input "abcdef" as the reservation code
        And I click the "Check" button
        Then the reservation information should be displayed

    Scenario: Make a reservation
        Given I have selected a trip
        When I click the "Buy" button
        And I enter valid payment information
        And I click the "Finish Purchase" button
        Then an alert should be displayed with the message "Purchase was successful!"
        And the reservation information should be displayed

    Scenario: Cancel a reservation
        When I input "abcdef" as the reservation code
        And I click the "Check" button
        And I click the "Cancel" button
        And I input "abcdefg" as the alteration code
        And I click the "Confirm" button
        Then an alert should be displayed with the message "Cancellation was successful!"

    Scenario: Choose different seat
        Given I have selected a trip
        When I choose a different seat
        Then the selected seat should change

    Scenario: Choose a more expensive seat
        Given I have selected a trip
        When I choose a more expensive seat
        Then the selected seat should change
        And the total price should increase

    Scenario: Choose a seat that is already taken
        Given I have selected a trip
        When I try to choose a seat that is already taken
        Then the selected seat should not change

    