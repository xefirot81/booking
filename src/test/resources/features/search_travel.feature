@search @travel
Feature: Search a travel

  Scenario: Search a travel
    Given I go to https://www.booking.com/
    Then I should see the web correctly charged
    When I click on close WELCOME button
    And I choose 'CURRENCY': 'Euro'
    And I choose 'LANGUAGE': 'English_US'
    And I click on close PERSONALIZED_SETTINGS button
    And I select Málaga, Andalucía, Spain option from DESTINATION dropdown with text on MAIN page
#    And I fill in 'Málaga, Andalucía, Spain' text in 'DESTINATION' field on 'MAIN' page
    And I click on CHECK_IN BUTTON on MAIN page
    And I select 'LAST' day of 'CURRENT' month
#    And I click on CHECK_OUT BUTTON on MAIN page
#    And I select 'FIRST' day of 'NEXT' month
    And I select '1' adult, '1' child '5' years old and '' rooms
    And I click on SEARCH BUTTON on MAIN page
    And I select '' adult, '' child '' years old and '2' rooms
    And I click on TRAVEL_BY_WORK CHECKBOX on RESULTS page
    And I click on SEARCH BUTTON on MAIN page
    Then There is a property with a review mark of higher than '8.0' and price under '200' EUR
#    And I report the name of first property found into Console.log