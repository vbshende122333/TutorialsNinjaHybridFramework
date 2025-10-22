Feature: Login Functionality

  @Smoke
  Scenario: Login with valid credentials for User 3
    Given User has navigate to login page
    When User enters valid email address "amotooticap9@gmail.com" into email field
    And User has entered valid password "12345" into password field
    And User clicks on login button
    Then User should get successfully logged in

  @Smoke
  Scenario Outline: Login with invalid credentials for User 4
    Given User has navigate to login page
    When User enters invalid email address <Email> into email field
    And User has entered invalid password <Password> into password field
    And User clicks on login button
    Then User should get Warning message
    Examples:
      | Email           | Password    |
      | (7)qab@abc.com  | ajdhajdada  |
      | (8)fqa@abc.com  | jhffjkwe    |
      | (9)hfq@abc.comd | 323232      |