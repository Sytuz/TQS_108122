## a) Identify a couple of examples that use AssertJ expressive methods chaining.

- In the 'A_EmployeeRepositoryTest' class, the test method 'givenSetOfEmployees_whenFindAll_thenReturnAllEmployees' uses the following AssertJ expression chaining: ```assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());```. This expression is used to assert that the list of employees has a size of 3 and that it contains only the names of the employees in the list.

- In the 'B_EmployeeService_UnitTest' class, the test method 'whenSearchValidName_thenEmployeeShouldBeFound' uses the following AssertJ expression chaining: ```assertThat(found.getName()).isEqualTo(name);```. This expression is used to assert that the name of the found employee is equal to the name of the employee that was searched for ("alex" in this case).

- Besides these examples, there are many other examples of AssertJ expressive methods chaining in the test classes of the sample project, although they don't vary much in terms of the methods used.

## b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

In the test class 'B_EmployeeService_UnitTest', in order to test the service layer without involving the database, the repository is mocked using the @Mock annotation. Before each test, the mock repository is initialized and its behavior is defined using the Mockito.when() method. 

## c) What is the difference between standard @Mock and @MockBean?

The @Mock annotation is used to create a mock object of a class or interface, which can be used to define the behavior of the object. This annotation is used in unit tests, where the focus is on testing a single class or method in isolation. The @MockBean annotation, on the other hand, is used to create a mock object of a bean in the Spring application context. This annotation is used in integration tests, where the focus is on testing the interaction between different components of the application. The @MockBean annotation is used to replace a real bean with a mock bean, so that the real bean is not used during the test.
(Source: https://stackoverflow.com/questions/44200720/difference-between-mock-mockbean-and-mockito-mock)

## d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The file "application-integrationtest.properties" is used to define the properties of the application when running integration tests. It essentially overrides the properties defined in the "application.properties" file, so that the application uses a different configuration when running integration tests.

The file is used when have the following annotation in the test class: ```@TestPropertySource(locations = "application-integrationtest.properties")```. This annotation is used to specify the location of the properties file that should be used when running the test class.

## e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

- C: The first strategy is to test the API using the @WebMvcTest annotation, which is used to test the controller layer of the application. This method allows for a more simplified and light environment, as it only loads the web layer of the application and not the entire application context. This is useful when the focus is on testing the web layer in isolation, without involving the service and repository layers.

- D: The second strategy is to test the API by loading the entire application context using the @SpringBootTest annotation. This method is used to test the entire application, including all the layers and components of the application, althought MockVC is used to simulate the HTTP requests (*"used to access the server context through a special testing servlet"*). This is useful when the focus is on testing the interaction between different components of the application, and when the service and repository layers are also involved in the test.

- E: The third strategy is similar to the second, but instead of using a mock server, the requester is a REST client (TestRestTemplate).