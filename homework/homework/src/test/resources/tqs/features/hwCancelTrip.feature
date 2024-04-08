@hwCancelTrip
Feature: Cancel Trip

    Background:
        Given I have bought a ticket
    
    Scenario: Cancel trip without refund
        Given my ticket is not refundable
        When I introduce the ticket code
        And click the "Check" button
        And click the "Cancel" button
        And input the alteration code
        And click the "Confirm" button
        Then a message is displayed with the cancellation confirmation
        But the message does not contain the refund amount
        And the ticket is no longer valid

    Scenario: Cancel trip with refund
        When I introduce the ticket code
        And click the "Check" button
        And click the "Cancel" button
        And input the alteration code
        And click the "Confirm" button
        Then a message is displayed with the cancellation confirmation
        And the message contains the refund amount
        And the ticket is no longer valid