@hwAlterReservation
Feature: Alter the reservation

    Background: 
        Given I have made a reservation

    Scenario: Alter the date of the reservation trip
        When I introduce the reservation code
        And click the "Check" button
        And click the "Alter" button
        And filter by the new date
        And select a trip from the list
        And click the "Alter" button
        Then the reservation trip date is altered

    # There are other scenarios that can be added here, depending on the time I will have to implement them
