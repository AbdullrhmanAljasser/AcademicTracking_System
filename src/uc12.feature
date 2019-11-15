@tag
Feature: UC12 Student drops course
	Background: 
		Given Server Running
		
  @tag1
  Scenario: Successfully drop a course
    Given Client has selected list of courses
    And Client in student menu and selected drop course
    And University Term has not ended
    And University Registration has begun
    When Client enters course to be dropped
    Then Course removed from student selected list

  Scenario: Unsuccessfully drop a course due term ending
    Given Client has selected list of courses
    And Client in student menu and selected drop course
    And University Term Ended
    And University Registration has begun
    When Client enters course to be dropped
    Then Course isn't removed from student selected list
    
  Scenario: Unsuccessfully drop a course due registration hasnt begun
    Given Client has selected list of courses
    And Client in student menu and selected drop course
    And University Term Ended
    And University Registration has not begun
    When Client enters course to be dropped
    Then Course isn't removed from student selected list