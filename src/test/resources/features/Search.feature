#Feature: Search Functionality
#
#  Scenario: User searches for a valid product
#    Given User opens the Application
#    When User enters valid product "HP" into Search box field
#    And User click on search button
#    Then User should get valid product displayed in search result
#
#  Scenario: User searches for an invalid product
#    Given User opens the Application
#    When User enters valid product "Honda" into Search box field
#    And User click on search button
#    Then User should get a message about no product matching
#
#  Scenario: User searches without any product
#    Given User opens the Application
#    When User don't enter any product in serach boc field
#    And User click on search button
#    Then User should get a message about no product matching
#
