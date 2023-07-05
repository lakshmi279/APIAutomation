Feature: Verify Gitlab Project service CRUD Operations

  @Regression
  Scenario Outline: Verify creating project in gitlab successfully using create project request
    Given User is an authorized user
    And   Valid  project name "<name>" given in the request payload
    When  User call the create project request
    Then  Project should be created successfully with response code 201
    And   The project name should start with "<name>"
    Examples:
      | name     |
      | Vanshika |
