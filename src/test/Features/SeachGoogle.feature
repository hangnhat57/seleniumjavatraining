Feature: Test Search Google

  Scenario: Enter keyword and search
    Given I navigate to Google page
    And I search for "facebook"
    Then I should be redirected to result page