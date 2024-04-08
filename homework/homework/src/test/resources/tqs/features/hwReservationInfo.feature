@hwReservationInfo
Feature: Check the reservation information

    Background:
        Given I am on the homepage
        And I have made a reservation

    Scenario: Check the reservation information
        When I introduce the reservation code
        And click the "Check" button
        Then I should see the reservation information

    Scenario: Give a wrong reservation code
        When I introduce an invalid reservation code
        And click the "Check" button
        Then I should see an error message

    Scenario: Give an empty reservation code
        When I leave the reservation code empty
        And click the "Check" button
        Then I should see an error message