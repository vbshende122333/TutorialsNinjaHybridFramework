Feature: Login Functionality

  @Regression
  Scenario: Login with valid credentials
    Given User has navigate to login page
    When User enters valid email address "amotooticap9@gmail.com" into email field
    And User has entered valid password "12345" into password field
    And User clicks on login button
    Then User should get successfully logged in

  @Regression
  Scenario Outline: Login with invalid credentials
    Given User has navigate to login page
    When User enters invalid email address <Email> into email field
    And User has entered invalid password <Password> into password field
    And User clicks on login button
    Then User should get Warning message
    Examples:
      | Email           | Password    |
      | abbqab@abc.com  | ajdhajdada  |
      | ajffqasdjakf    | jhffjkwe    |
      | dadhfqffskjkfkd | 323232      |