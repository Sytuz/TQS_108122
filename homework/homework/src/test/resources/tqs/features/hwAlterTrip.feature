@hwAlterTrip
Feature: Alter the date of the trip

    Background: 
        Given I have bought a ticket

    Scenario: Alter the date of the trip
        When I introduce the ticket code
        And click the "Check" button
        And click the "Alter" button
        And filter by the new date
        And select a trip from the list
        And click the "Alter" button
        Then the trip date is altered

    # There are other scenarios that can be added here, depending on the time I will have to implement them
