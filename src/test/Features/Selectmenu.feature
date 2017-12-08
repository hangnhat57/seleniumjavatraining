Feature: Selectmenu
  Scenario: I want to handle Jquery select
    Given I navigate to "https://jqueryui.com/selectmenu/#default"
    When I click on Select a speed drop down list
    And I choose "Fast" option
    Then The Select a speed drop down list should display "Fast"