@hwTicketInfo
Feature: Check the ticket information

    Background:
        Given I am on the homepage
        And I have bought a ticket

    Scenario: Check the ticket information
        When I introduce the ticket code
        And click the "Check" button
        Then I should see the ticket information

    Scenario: Give a wrong ticket code
        When I introduce an invalid ticket code
        And click the "Check" button
        Then I should see an error message

    Scenario: Give an empty ticket code
        When I leave the ticket code empty
        And click the "Check" button
        Then I should see an error message