# Solar Analysis

## e) Has your project passed the defined quality gate? Elaborate your answer.

Yes, the project has passed the defined quality gate (built-in 'Sonar way').

Reading through the report, there are 0 security issues, 0 reliability issues, but there are a total of 31 maintainability issues, most of them low to medium impact and 1 high impact. The technical debt to fix all of them is 1h39min.

Checking the issue tags, most of them are related to junit issues, and a large part can be solved using SolarLint. The high impact issue is 'Define a constant instead of duplicating this literal "duplicate value: " 3 times.'.

There is 1 security hotspot, which is due to a pseudorandom number generator being used (which is not cryptographically secure). This generator was implemented in the original code.

The total coverage is 80.7%, with a total of 11 unit tests, all of them successful.

## f) Explore the analysis results and complete with a few sample issues, as applicable.

There are no bugs nor vulnerabilities, and most of the issues (which are only code smells) are related to the same problems. Here are a few examples:

| Issue | Problem description | How to solve |
| --- | --- | --- |
| Literal duplications | The literal "duplicate value: " is duplicated 3 times. | Define a constant instead of duplicating this literal 3 times. |
| JUnit assertions | JUnit assertTrue/assertFalse should be simplified to the corresponding dedicated assertion. | Use assertNotEquals instead. (in this specific case) |
| Loop counter within the loop body | The loop counter is incremented within the loop body. | Move the increment from the loop body to the update clause of the "for" statement. |