Feature: Create customer
  As a user of my demo rest-api-app
  I want to create customers
  So that I can ...

  Scenario: Create valid customer
    Given I am logged in the app
      | username | password |
      | admin    | password |
    When I create a new customer
      | first_name | last_name |
      | Bruno      | Krebs     |
    Then I should be able to see customer's data
      | first_name | last_name |
      | Bruno      | Krebs     |

  Scenario: Try to create customer without be logged in the app
    Given I am not logged in the app
    When I create a new customer
      | first_name | last_name |
      | Bruno      | Krebs     |
    Then Customer creation should be rejected with status code 403

  Scenario: Create customer with invalid data
    Given I am logged in the app
      | username | password |
      | admin    | password |
    When I create a new customer
      | first_name | last_name |
      |            | Krebs     |
      | Bruno      |           |
      |            |           |
    Then Customer creation should be rejected with status code 400
