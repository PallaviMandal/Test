Feature: Validate user is successfully navigated to nth search result on search engine

  Background: User launch browser and open search engine
    Given User Launch "Chrome" Browser
    Then User opens "google" search engine


  Scenario Outline: Validate user landed on correct web page
    And User searches for "<searchTerm>" search term
    Then User clicks on the "<nth>" search result
    Then User navigate to webpage having search term "<searchTerm>"
    Then User validates the redirected URL

    Examples:
      | searchTerm           | nth  |
      | selenium             | 1st  |
      | latest movie         | 7th  |
      | locators in selenium | 10th |
