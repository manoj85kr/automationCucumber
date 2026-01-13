@sanity
Feature: Login Functionality

  @login @smoke  
  Scenario: Successful login with valid credentials
    Given User is on login page
    When User enters username "manojranganathanofficial@gmail.com" and password "Madan123$"
    Then User should be logout

  @login
  Scenario: To verify system identifies wrong credentials
    Given User is on login page
    When User enters username "manojranganathanofficial2@gmail.com" and password "Madan123$"
    And User should be able to view the error message as "Invalid details. Please check the Email ID - Password combination."
