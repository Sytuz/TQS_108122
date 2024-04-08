@hwChooseSeat
Feature: Choose seat

    Background:
        Given I have selected a trip
    
    Scenario: Choose different seat
        When I choose a different seat
        Then the selected seat should change

    Scenario: Choose a more expensive seat
        When I choose a more expensive seat
        Then the selected seat should change
        And the total price should increase

    Scenario: Choose a less expensive seat
        Given I have selected a more expensive seat
        When I choose a less expensive seat
        Then the selected seat should change
        And the total price should decrease

    Scenario: Choose a seat that is already taken
        When I try to choose a seat that is already taken
        Then the selected seat should not change