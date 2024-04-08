@hwCancelReservation
Feature: Cancel Reservation

    Background:
        Given I made a reservation
    
    Scenario: Cancel trip without refund
        Given my reservation is not refundable
        When I introduce the reservation code
        And click the "Check" button
        And click the "Cancel" button
        And input the alteration code
        And click the "Confirm" button
        Then a message is displayed with the cancellation confirmation
        But the message does not contain the refund amount
        And the reservation is no longer valid

    Scenario: Cancel trip with refund
        When I introduce the reservation code
        And click the "Check" button
        And click the "Cancel" button
        And input the alteration code
        And click the "Confirm" button
        Then a message is displayed with the cancellation confirmation
        And the message contains the refund amount
        And the reservation is no longer valid