@tag
Feature: UC7 Clerk deletes student
	Background: 
		Given Server Running
  @tag1
  Scenario: Successfully delete student
    Given Client in Clerk menu and selected delete student
    And University Term has not ended
    And University Registration has not begun
    And Student does exists
    When Client submit student info to be deleted
    Then Student is deleted successfully

  Scenario: Unsuccessfully delete student due to term ended
    Given Client in Clerk menu and selected delete student
    And University Term Ended
    And University Registration has not begun
    And Student does exists
    When Client submit student info to be deleted
    Then Student isn't deleted
    
  Scenario: Unsuccessfully delete student due to registration begun
    Given Client in Clerk menu and selected delete student
    And University Term has not ended
    And University Registration has begun
    And Student does exists
    When Client submit student info to be deleted
    Then Student isn't deleted
    
  Scenario: Unsuccessfully delete student due to student non-existence
    Given Client in Clerk menu and selected delete student
    And University Term has not ended
    And University Registration has begun
    And Student does not exists
    When Client submit student info to be deleted
    Then Student isn't deleted and informed about non-existence