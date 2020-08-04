Feature: Login
  As a user of my demo rest-api-app
  I want to login into the app
  So that I can interact with the application

  Background:
    Given I am registered in the app with:
      | username | password |
      | Aslak    | Hellesoy |

  Scenario: Successful login
    When I login with credentials:
      | username | password |
      | Aslak    | Hellesoy |
    Then I should be logged in successfully

  Scenario Outline: Wrong credentials
    When I login with credentials:
      | username   | password   |
      | <username> | <password> |
    Then Login should be rejected with status code <code>
    Examples:
      | username | password | code |
      | Aslak    | temp     | 401  |
      | Temp     | Hellesoy | 401  |
      |          | Hellesoy | 401  |
      | Aslak    |          | 401  |
      |          |          | 401  |