@hwChangeCurrency
Feature: Change currency

    Background:
        Given I am on the homepage

    Scenario: Change currency to USD
        When I click on the currency dropdown
        And I select USD
        Then the currency should be changed to USD

    Scenario: Change currency after choosing a cheaper seat
        Given I have selected a trip
        And I have chosen a cheaper seat
        When I click on the currency dropdown
        And I select USD
        Then the currency should be changed to USD
        And the price should be updated