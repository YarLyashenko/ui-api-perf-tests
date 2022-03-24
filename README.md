# Screening project for Toptal

___

### Task 1:

##### Write an automated test for an e-commerce site:

* Pick one of your favorite e-commerce websites
* You need to implement following tests:
    * Login
    * Searching products by three criteria
    * Adding products to the cart
    * Removing products from the cart
    * Checkout process
    * If possible, implement a sign-up / registration test
* Add an HTML report of test results
* Report any bugs you find by writing a bug ticket
* Please prepare a document with the test flows and test cases. The documents have to be clear both to the developer and
  to someone who is not familiar with the technology.
* Run tests in a continuous integration tool and optionally in the cloud

### Solution 1:

UI tests for www.8a.pl store with sports equipment. Using maven, Selenide, jUnit5, Allure

##### To execute test:

Prerequisites: jdk11, maven, docker

```bash
cd ui-tests-8a-pl
mvn clean test allure:serve
```

##### [Test run report](https://yarlyashenko.github.io/ui-tests-allure-report/) | [CircleCI Job](https://app.circleci.com/pipelines/github/YarLyashenko/ui-api-perf-tests) | [Test cases](ui-tests-8a-pl/TestCases.md) | [Bug](ui-tests-8a-pl/BUG_SearchIconDisabledAction.md)

___

### Task 2:

##### Write an automated test for a REST API service

* Implement REST API tests for some of “location” services by your choice. The idea of this test is to implement tests
  for creating data, modifying existing data or deleting data. For example, you can create your own map with pins on it.
* You may pick any REST API provider which offers such service (as long it’s free to use).
* Implement test cases of sending location information to the map. Test sending proper information, invalid information,
  incorrect format, and other possible edge cases.
* Test for at least three different HTTP response codes in your tests. For example, 200 OK, 401 Unauthorized or 304 Not
  Modified.
* Add an HTML report of test results
* Report any bugs you find by writing a bug ticket

### Solution 2:

API tests for cartes.io markers endpoint. Using maven, restassured, jUnit5, Allure

##### To execute test:

Prerequisites: jdk11, maven

```bash
cd api-tests-cartes-io 
mvn clean test allure:serve
```

##### [Test run report](https://yarlyashenko.github.io/api-tests-allure-report/)  |  [Bug](api-tests-cartes-io/BUG_PUT_marker.md)

___

### Task 3:

##### Pick a random web application and create a load test with a tool of your choice but using an HTTP/S protocol. Load test needs to simulate 1000 users who will visit the homepage in a period of 15s. Measure web application response time before and during the test run.

* Explain the test in details
* Did the load test have an impact on web application response time?
* What is the optimal application response time for modern day web applications?
* Analyze few HTTP/S responses

### Solution 3:

Performance tests for www.4home.pl webpage. Using maven, jmeter.

##### To execute test:

Prerequisites: jdk11, maven

```bash
cd perf-tests-4home-pl 
mvn clean verify
```

##### [Test run report](https://yarlyashenko.github.io/perf-test-jmeter-report/)

##### Test steps:

1. Send 1 Get request to https://www.4home.pl to measure response time without load
2. Wait 2 seconds to be sure nothing affecting next step
3. Run 1000 GET requests to https://www.4home.pl without delay during 15s. It should give throughput 4000 rps. Each
   request has check for status code (expected 200) and page title.

##### Test results:

* Web application is quite weak. One request is performed in around 1s, but with increased load response time goes up to
  30+ seconds.
* Each request has correct response, only response time is affected. I could assume such load is not manageable and
  application is an easy target for DDOS attack.
* Test results are unacceptable for modern application. However, this web store could never achieve such numbers of
  concurrent customers.
* This test takes into consideration only backend performance. Frontend (browser) performance is also quite poor for
  modern application, and store should invest more into speed and usability.
* [Lighthouse report](perf-tests-4home-pl/Lighthouse_report.pdf)