@hwFilterTrips
Feature: Filter trips

    Background:
        Given I am on the homepage

    Scenario: Filter trips by origin and destination
        When I choose "Viseu" as the origin and "Lisboa" as the destination
        Then I should see a list of trips from "Viseu" to "Lisboa"

    Scenario: Filter trips by origin, destination and date
        When I choose "Viseu" as the origin, "Lisboa" as the destination and "08-04-2024" as the date
        Then I should see a list of trips from "Viseu" to "Lisboa" on "08-04-2024"
        
    Scenario: Check if origin cannot be the same as destination
        When I choose "Viseu" as the origin
        Then "Viseu" should not be available as a destination