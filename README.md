TestingBot TestNG Example
============

What this example contains
-------------------------

 This example contains a TestNG test file with 1 test: `testSimple`.
 The test will start a real browser on TestingBot.com and will instruct that browser to navigate to https://testingbot.com where it will verify if the H1 is correct.

 Once the test completes, the included `TestingBotTestListener` will send the test success state and testname back to TestingBot, via the TestingBot REST API.

 With this example you will be able to run a TestNG test via TestingBot and see its meta-data on TestingBot.com


Running the Test
----------------------

 Before you can run the test, please adjust the `getTestingBotKey` and `getTestingBotSecret` values in the TestingBotTest example to use your own key and secret, which you can get for
 free from [https://testingbot.com/](https://testingbot.com/)

 Once you've added your own key and secret to authenticate to our grid, use NetBeans or Eclipse to run the TestNG test.


TestingBotTestListener
----------------------

 In order for TestingBot.com to be able to show the test success state and test name, it will need to receive this data via the TestingBot REST API.
 We've made a `TestingBotTestListener.java` class in this example that interacts with TestNG to send this meta-data when the tests finish running.

 To include this listener with your own test classes, please use:

```java
@Listeners({TestingBotTestListener.class})
public class TestingBotTest implements TestingBotSessionIdProvider, TestingBotAuthenticationProvider {
```

More info
-----------

 More information is available on our support pages: 

[https://testingbot.com/support/getting-started/java.html](https://testingbot.com/support/getting-started/java.html)
