Feature: Create customer
  As a user of my demo rest-api-app
  I want to create customers
  So that I can ...

  Background:
    Given I am logged in the app
      | username | password |
      | admin    | password |
    And I create a new customer
      | first_name | last_name  |
      | User To    | Be Updated |

  Scenario: Update customer
    When I update customer information
      | first_name | last_name     |
      | New name   | New last name |
    Then I should be able to see customer's data
      | first_name | last_name     |
      | New name   | New last name |

  Scenario Outline: Update customer with null values
    When I update customer information
      | first_name   | last_name   |
      | <first_name> | <last_name> |
    Then Customer update should be rejected with status code 400
    Examples:
      | first_name | last_name     |
      |            | New last name |
      | New name   |               |
      |            |               |

  Scenario: Try to update customer without be logged in the app
    When I am not logged in the app
    And I update customer information
      | first_name | last_name     |
      | New name   | New last name |
    Then Customer creation should be rejected with status code 403
