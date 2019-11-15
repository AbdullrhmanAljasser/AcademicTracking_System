
Feature: UC5 Client cancel course
  Background: 
		Given Server Running

  Scenario: Successfully cancel course
    Given Client in Clerk menu and selected cancel course
    And Course exists
    And University Term has not ended
    And University Registration has ended
    When Client submit course code to be cancelled
    Then Course isn't removed but students are deregistered from it

  Scenario: Unsuccessfully cancel course due to term ending
    Given Client in Clerk menu and selected cancel course
    And Course exists
    And University Term Ended
    And University Registration has ended
    When Client submit course code to be cancelled
    Then Course isn't removed and students aren't deregistered from it
    
  Scenario: Unsuccessfully cancel course due to registartion hasnt ended
    Given Client in Clerk menu and selected cancel course
    And Course exists
    And University Term has not ended
    And University Registration has not ended
    When Client submit course code to be cancelled
    Then Course isn't removed and students aren't deregistered from it