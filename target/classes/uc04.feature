
Feature: UC4 Client deletes course
  Background: 
		Given Server Running


  Scenario: Successfully delete a course
    Given Client in Clerk menu and selected destroy course
    And Course exists
    And University Term has not ended
    And University Registration has not begun
    When Client submit course code to be deleted
    Then Course is removed from university


  Scenario: Unsuccessfully delete a course due to course non-existing
    Given Client in Clerk menu and selected destroy course
    And University Term Ended
    And University Registration has not begun
    And Course doesn't exists
    When Client submit course code to be deleted
    Then Course is not removed from university and clerk returned to selection



  Scenario: Unsuccessfully delete a course due to term ended
    Given Client in Clerk menu and selected destroy course
    And Course exists
    And University Term Ended
    And University Registration has not begun
    When Client submit course code to be deleted
    Then Course is not removed from university
    
  Scenario: Unsuccessfully delete a course due to registration
    Given Client in Clerk menu and selected destroy course
    And Course exists
    And University Term has not ended
    And University Registration has begun
    When Client submit course code to be deleted
    Then Course is not removed from university