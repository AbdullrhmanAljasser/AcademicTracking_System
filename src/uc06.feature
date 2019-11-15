@tag
Feature: UC6 Clerk creates student
	Background: 
		Given Server Running

  @tag1
  Scenario: Successfully Create Student
    Given Client in Clerk menu and selected create student
    And University Term has not ended
    And University Registration has not begun
    And Student does not exists
    When Client submit student info to be created
    Then Student is successfully created

  Scenario: unSuccessfully Create Student due to term ending
    Given Client in Clerk menu and selected create student
    And University Term Ended
    And University Registration has not begun
    And Student does not exists
    When Client submit student info to be created
    Then Student is unsuccessfully created
    
  Scenario: unSuccessfully Create Student due to registration begun
    Given Client in Clerk menu and selected create student
    And University Term has not ended
    And University Registration has begun
    And Student does not exists
    When Client submit student info to be created
    Then Student is unsuccessfully created
    
  Scenario: unSuccessfully Create Student due to student exist
    Given Client in Clerk menu and selected create student
    And University Term has not ended
    And University Registration has not begun
    And Student does exists
    When Client submit student info to be created
    Then Student is unsuccessfully created, but exists