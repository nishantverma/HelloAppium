Feature: Hello World

  @tag1
  Scenario: Registration Flow Validation via app
  As a User I should be able to see my google account
  when I try to register myself in Quikr app

    When I launch Quikr app
    And I choose to log in using Google
    Then I see account picker screen with my email address "vagrantlab@gmail.com"

  @search
  Scenario: Search for a used Honda City car in Bangalore city

    When I launch Quikr app
    And I choose "Bangalore" as my city
    And I search for "Honda City" under Used Cars
    Then I should see the first car search result with "Honda"

  @tag2
  Scenario: Registration Flow Validation via web
  As a User I want to verify that
  I get the option of choosing Facebook when I choose to register

    When I launch Quikr mobile web
    And I choose to register
    Then I should see an option to register using Facebook