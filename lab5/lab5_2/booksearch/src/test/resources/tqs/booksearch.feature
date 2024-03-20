Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

    Background:
        Given a library with the following books:
            | Title           | Author         | Published     |
            | One good book   | Anonymous      | 2013 March 14 |
            | Some other book | Tim Tomson     | 2014 August 23|
            | How to cook a dino | Fred Flintstone | 2012 January 01|
             
    Scenario: Search books by publication year
        When the customer searches for books published between 2013 and 2014
        Then 2 books should have been found
            And Book 1 should have the title 'Some other book'
            And Book 2 should have the title 'One good book'
    
    Scenario: Search books by author
        When the customer searches for books written by 'Tim Tomson'
        Then 1 books should have been found
            And Book 1 should have the title 'Some other book'
        
    Scenario: Search books by title
        When the customer searches for books with the title 'One good book'
        Then 1 books should have been found
            And Book 1 should have been written by 'Anonymous'
            And Book 1 should have been published in 2013 March 14


