@calc_sample
Feature: Basic Arithmetic Operations

    Background: A Calculator
        Given a calculator I just turned on
    
    Scenario Outline: Addition
        When I add <a> and <b>
        Then the result is <result>

    Examples:
        | a | b | result |
        | 2 | 2 | 4      |
        | 3 | 3 | 6      |
        | 4 | 4 | 8      |
        | 2 | 1 | 3      |
        | 5 | 5 | 10     |
        | 2 | 9 | 11     |

    Scenario Outline: Subtraction
        When I subtract <a> from <b>
        Then the result is <result>

    Examples:
        | a | b | result |
        | 2 | 2 | 0      |
        | 5 | 2 | 3      |
        | 8 | 3 | 5      |
        | 10| 5 | 5      |
        | 12| 6 | 6      |

    Scenario Outline: Multiplication
        When I multiply <a> and <b>
        Then the result is <result>

    Examples:
        | a | b | result |
        | 2 | 2 | 4      |
        | 3 | 3 | 9      |
        | 4 | 4 | 16     |
        | 2 | 1 | 2      |
        | 5 | 5 | 25     |
        | 2 | 9 | 18     |

    Scenario Outline: Division
        When I divide <a> by <b>
        Then the result is <result>

    Examples:
        | a  | b | result |
        | 4  | 2 | 2      |
        | 9  | 3 | 3      |
        | 16 | 4 | 4      |
        | 2  | 1 | 2      |
        | 25 | 5 | 5      |
        | 18 | 9 | 2      |

    Scenario: Division by Zero
        When I divide 5 by 0
        Then a failure occurs

    Scenario: Invalid Operation
        When I perform an invalid operation
        Then a failure occurs