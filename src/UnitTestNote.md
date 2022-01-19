* JUnit is a simple framework to write repeatable tests
  * JUnit provides a simple api to test our java application.
  * A class or method we want to test is usually called as subject under test
  * JUnit is a combination of three different modules 
    * Junit platform is the module which provides an api to launch the test from either the Ide, build tools or console.
    * So when you write a unit test using JUnit using your Ide or build tools or console uses this api inside the platform module to launce the test
    * Junit Jupiter: provides an api to write Junit test and extensions.
    * Junit Vintage: provides a test engine implementation to support backward compatibility for tests written with Junit 3 or Junit 4
    * 3rd Part Testing Frameworks: to build their own api to write the tests and reuse the Junit platform module to launch the tests -> this is another update in Junit5

* Add dependency to maven: junit jupiter
* Generate Test Automatically: Go to class need create a test: Ctrl Shift t
* How to use:
  * Add annotation @Test so that Junit understand this is a test
  * @DisplayName: custom name for your test
  * Assertions: Junit provide and inside the class, 
    * using assetFalse method (this method will take a boolean parameter as input and if the boolean is not false it will throw an exception and will fail the test)
    * using assetEquals method (this method takes the expected value as the first parameter and the actual value as this has the next parameter ) 
  * Testing Exceptions using assertThrows()
* Junit Test Lifecycle
  * Each Junit test when it is executed will create a new instance of the test class and if follows different phases as part of the execution each phase is also representd with an annotation in Junit 5
    * @BeforeAll: will be executed before any of the test methods inside the test class.
    * @BeforeEach: will be executed before each of the methods inside test class.
    * @AfterEach: will be executed after each test method inside the test class
    * @AfterAll: will be executed after all method.
  