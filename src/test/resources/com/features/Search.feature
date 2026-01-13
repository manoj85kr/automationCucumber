@search
Feature: Search the job functionality

  Background:
    Given User is on login page
    When User enters username "manojranganathanofficial@gmail.com" and password "Madan123$"

  @smoke
  Scenario: search for the automation testing skill set with 13 years of experience and location in chennai
    Given user clicks on the search bar
    And wait for the search banner is opened
    Then enter the keyword as "Automation Test Engineer"
    And select the experience dropdown with value as "13 years"
    And enter the location as "Chennai"
    Then click on the search button

  @multipleData @smoke
  Scenario Outline: Search with multiple data sets
    Given user clicks on the search bar
    And wait for the search banner is opened
    When user enters job search details
      | keyword    | <keyword>    |
      | experience | <experience> |
      | location   | <location>   |
    And click on the search button
    Then user gets the search results

    Examples:
      | keyword                  | experience | location |
      | Automation Test Engineer | 8 years    | Chennai  |
      | QA Lead                  | 9 years    | Chennai  |
      | Selenium Testing         | 10 years   | Chennai  |
      | Manual Testing           | 11 years   | Chennai  |
      | WebDriver Selenium       | 12 years   | Chennai  |
      | Test Engineer            | 13 years   | Chennai  |

  @recommendedjobsProfile @recommendedjobs
  Scenario: User view the profile recommended jobs for you
    Given user navigates to the recommended jobs section
    When user clicks on the "Profile" tab in recommended jobs for you
    And user gets the recommended jobs search results

  @recommendedjobsTopCandidate @recommendedjobs
  Scenario: User view the top candidate recommended jobs for you
    Given user navigates to the recommended jobs section
    When user clicks on the "Top Candidate" tab in recommended jobs for you
    And user gets the recommended jobs search results

  @recommendedjobsYoumightlike @recommendedjobs
  Scenario: User view the you might like recommended jobs for you
    Given user navigates to the recommended jobs section
    When user clicks on the "You might like" tab in recommended jobs for you
    And user gets the recommended jobs search results

  @recommendedjobsPreferences @recommendedjobs
  Scenario: User view the preferences recommended jobs for you
    Given user navigates to the recommended jobs section
    When user clicks on the "Preferences" tab in recommended jobs for you
    And user gets the recommended jobs search results

  @recommendedjobsApplies @recommendedjobs
  Scenario: User view the applies recommended jobs for you
    Given user navigates to the recommended jobs section
    When user clicks on the "Applies" tab in recommended jobs for you
    And user gets the recommended jobs search results
