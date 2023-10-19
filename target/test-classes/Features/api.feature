@regression
Feature: Rest-assured API functionality

  @sanity
  Scenario: Get API request
    Given The API endpoint is "https://reqres.in/api"
    When A GET request is sent
    Then Status response is 200

  Scenario: Post API request
    Given The API endpoint is "https://reqres.in/api"
    When A POST request is sent
    Then Status response is 201 for post