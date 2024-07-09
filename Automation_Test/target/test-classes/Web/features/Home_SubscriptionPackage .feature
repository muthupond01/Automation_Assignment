Feature: To Automate the STC Subscribe TV

  Scenario Outline: Validate the Subscription Packages – Type & Price and Currency for the country -  "<country>"
    Given user launches the stc home page
    When user switches to country "<country>"
    Then user should see the plan names
    And user should see the monthly prices "<country>"

    Examples: 
      | country |
      | ksa     |
      | kuwait  |
      | bahrain |
