#Feature: Register Account
#  As a new user
#  I want to register an account
#  So that I can log in and use the site
#
#  Background: Open Register page
#    Given User launches the application
#    And User navigates to the Register Account page
#
#  @smoke @positive
#  Scenario: Successful registration with valid details using table
#    When User fills the registration form with:
#      | First Name | Rohan |
#      | Last Name | Sharma |
#      | E-Mail | rohan.sharma+ts1@example.com |
#      | Telephone | 9876543210 |
#      | Password | Qwerty@123 |
#      | Password Confirm | Qwerty@123 |
#      | Newsletter | No |
#      | Privacy Policy | Yes |
#    And User clicks Continue
#    Then the account should be created successfully
#    And a success message "Your Account Has Been Created!" should be displayed
#    And User should be redirected to the account dashboard
#
#  @negative
#  Scenario Outline: Mandatory field validation messages
#    When User leaves <field> blank
#    And User fills all other fields with valid data
#    And User accepts the Privacy Policy
#    And User clicks Continue
#    Then an inline validation message "<message>" should be displayed for <field>
#    And the account should not be created
#
#    Examples:
#      | field            | message                                                  |
#      | First Name       | First Name must be between 1 and 32 characters!         |
#      | Last Name        | Last Name must be between 1 and 32 characters!          |
#      | E-Mail           | E-Mail Address does not appear to be valid!             |
#      | Telephone        | Telephone must be between 3 and 32 characters!          |
#      | Password         | Password must be between 4 and 20 characters!           |
#      | Password Confirm | Password confirmation does not match password!          |
#  @negative
#  Scenario: Registration blocked when Privacy Policy not accepted
#    When User fills all fields with valid data
#    And User does not accept the Privacy Policy
#    And User clicks Continue
#    Then an alert "Warning: You must agree to the Privacy Policy!" should be displayed
#    And the account should not be created
#
#  @negative
#  Scenario Outline: Invalid email formats are rejected
#    When User fills the registration form with:
#      | First Name | Rohan |
#      | Last Name | Sharma |
#      | E-Mail | <email> |
#      | Telephone | 9876543210 |
#      | Password | Qwerty@123 |
#      | Password Confirm | Qwerty@123 |
#      | Newsletter | No |
#      | Privacy Policy | Yes |
#    And User clicks Continue
#    Then an inline validation message "E-Mail Address does not appear to be valid!" should be displayed for E-Mail
#
#    Examples:
#      | email             |
#      | rohan.example.com |
#      | rohan@            |
#      | @example.com      |
#      | rohan@com         |
#  @negative
#  Scenario: Duplicate email should not register
#    Given an account already exists for E-Mail "rohan.dup@example.com"
#    When User fills the registration form with:
#      | First Name | Rohan |
#      | Last Name | Sharma |
#      | E-Mail | rohan.dup@example.com |
#      | Telephone | 9876543210 |
#      | Password | Qwerty@123 |
#      | Password Confirm | Qwerty@123 |
#      | Newsletter | No |
#      | Privacy Policy | Yes |
#    And User clicks Continue
#    Then an alert "Warning: E-Mail Address is already registered!" should be displayed
#    And the account should not be created
#
#  @negative
#  Scenario Outline: Password and confirm mismatch
#    When User fills the registration form with:
#      | First Name | Rohan |
#      | Last Name | Sharma |
#      | E-Mail | rohan.mismatch@example.com |
#      | Telephone | 9876543210 |
#      | Password | <pwd> |
#      | Password Confirm | <confirm> |
#      | Newsletter | No |
#      | Privacy Policy | Yes |
#    And User clicks Continue
#    Then an inline validation message "Password confirmation does not match password!" should be displayed for Password Confirm
#
#    Examples:
#      | pwd         | confirm     |
#      | Qwerty@123  | Qwerty@12   |
#      | Hello@2025  | hello@2025  |
#  @boundary
#  Scenario Outline: Field length boundaries
#    When User fills the registration form with:
#      | First Name | <fname> |
#      | Last Name | <lname> |
#      | E-Mail | boundary+<tag>@example.com |
#      | Telephone | <phone> |
#      | Password | <pass> |
#      | Password Confirm | <pass> |
#      | Newsletter | No |
#      | Privacy Policy | Yes |
#    And User clicks Continue
#    Then the form should show "<expected>"
#
#    Examples:
#      | tag | fname  | lname  | phone                            | pass                 | expected                                         |
#      | 01  | A      | B      | 123                              | 1234                 | success                                          |
#      | 02  |        | B      | 12                               | 123                  | First Name must be between 1 and 32 characters! |
#      | 03  | A...32 | B...32 | 12345678901234567890123456789012 | 12345678901234567890 | success                                          |
#      | 04  | A...33 | B      | 1                                | 123                  | First Name must be between 1 and 32 characters! |
#  @accessibility
#  Scenario: Keyboard-only submission and required asterisks
#    When User tabs through the fields in order
#    Then the focus should move sequentially: First Name, Last Name, E-Mail, Telephone, Password, Password Confirm, Newsletter, Privacy Policy, Continue
#    And all mandatory fields should be marked with an asterisk
#
#  @newsletter
#  Scenario Outline: Toggle newsletter subscription
#    When User fills all fields with valid data
#    And User selects Newsletter "<choice>"
#    And User accepts the Privacy Policy
#    And User clicks Continue
#    Then the account should be created successfully
#    And the user’s newsletter preference should be saved as "<choice>"
#
#    Examples:
#      | choice |
#      | Yes    |
#      | No     |