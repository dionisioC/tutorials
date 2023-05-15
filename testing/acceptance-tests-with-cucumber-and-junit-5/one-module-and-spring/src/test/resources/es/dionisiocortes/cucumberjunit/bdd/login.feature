Feature: Login
  Scenario: Admin login
    Given admin user wants to login
    When the user tries to login as admin
    Then the user is allowed to use the app.

  Scenario: Other user login login
    Given other user wants to login
    When the user tries to login
    Then the user is not allowed to use the app.