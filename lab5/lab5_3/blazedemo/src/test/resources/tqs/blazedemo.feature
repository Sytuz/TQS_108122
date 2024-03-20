Feature: Buy plane tickets using BlazeDemo

    Scenario: Choosing the departure and destination cities
        Given I am on the home page
        When I select "San Diego" as departure city
        And I select "New York" as destination city
        And I click the 'Find Flights' button
        Then I should see the departure city as "San Diego" and the destination city as "New York"
        And the webpage title should be "BlazeDemo - reserve"

    Scenario: Choosing a flight
        Given I am on the home page
        When I select "San Diego" as departure city
        And I select "New York" as destination city
        And I click the 'Find Flights' button
        And I select the "1st" flight
        Then I should see the 'Your flight from TLV to SFO has been reserved.' message
        And the webpage title should be "BlazeDemo Purchase"

    Scenario: Choosing and buying a ticket
        Given I am on the home page
        When I select "San Diego" as departure city
        And I select "New York" as destination city
        And I click the 'Find Flights' button
        And I select the "1st" flight
        And I fill the form
        And I click the 'Purchase Flight' button
        Then I should see the 'Thank you for your purchase today!' message
        And the webpage title should be "BlazeDemo Confirmation"
