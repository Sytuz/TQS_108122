# 2c - Assess the coverage level in project “Euromillions-play”

-  ## Which classes/methods offer less coverage?

There are no classes that offer 100% coverage. The class with the least coverage is "DemoMain", with a coverage of 0%, followed by "CuponEuromillions" at 34% and "BoundedSetOfNaturals" at 54%.

- ## Are all possible [decision] branches being covered?

No. For example, "BoundedSetOfNaturals" has a method "add" that has a branch that is not being covered.

- ## Collect evidence of the coverage for “BoundedSetOfNaturals”

The coverage for "BoundedSetOfNaturals" is 54%. There are 4 methods in this class that are not being covered at all, which are "fromArray", "hashCode", "size" and "intersects". Additionally, the methods "add" and "equals" have branches that are not being covered.

- ## What kind of unit test are worth writing for proper validation of BoundedSetOfNaturals?

By looking at the code, we can make a list of tests that would complete the coverage of the class "BoundedSetOfNaturals":
- Test the method "fromArray" with an array of integers.
- Test the method "hashCode" using an instance of the class.
- Test the method "size" using an instance of the class.
- Test the method "intersects" using two instances of the class.
- Test the method "add" using instances of the class and values that trigger the branches that are not being covered.
- Test the method "equals" using instances of the class and values that trigger the branches that are not being covered.

# 2d - Run Jacoco coverage analysis and compare with previous results. In particular, compare the “before” and “after” for the BoundedSetOfNaturals class

The coverage for "BoundedSetOfNaturals" is now 100%. All methods are being covered and all branches are being tested. The coverage for the other classes remains the same.

I made some changes to the existing tests from "BoundedSetOfNaturalsTest" and added new tests to cover the missing branches. I also implemented the method "intersects" from the class "BoundedSetOfNaturals" and made some changes to the method "add" (version with list of integers as parameter) in order to follow the same logic as the other "add" method (version with single integer as parameter).