@adddevice
Feature: To add a new device through api call

  Scenario Outline: To create new booking using cucumber Data Table
    Given user has access to endpoint "/objects"
    When user adds a new device "<name>" "<year>" "<price>" "<cpumodel>" "<harddisksize>"
    Then user should get the response code 200
		And user verifies the new device added "<name>" "<year>" "<price>" "<cpumodel>" "<harddisksize>" in response
    Examples: 
      | name   				   | year | price   | cpumodel     | harddisksize   |
      | Apple Max Pro 1TB| 2023 | 7999.99 | Apple ARM A7 | 1 TB           |
 