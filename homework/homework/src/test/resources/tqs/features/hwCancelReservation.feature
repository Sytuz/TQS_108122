@hwCancelReservation
Feature: Cancel Reservation

    Background:
        Given I made a reservation
    
    Scenario: Cancel trip
        Given my reservation is not refundable
        When I introduce the reservation code
        And click the "Check" button
        And click the "Cancel" button
        And input the alteration code
        And click the "Confirm" button
        Then a message is displayed with the cancellation confirmation
        # And the reservation is no longer valid