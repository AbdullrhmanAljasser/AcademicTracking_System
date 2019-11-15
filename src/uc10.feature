Feature: UC10 Client register for course as student
	Background: 
		Given Server Running
		
  @tag1
  Scenario: Successfully Register for a course
    Given Client in Student menu and has courses selected
    And Client in student menu and selected register for coruse
    And University Term has not ended
    And University Registration has begun
    And University Registration has not ended
    When Client enters the code of the selected course to register
    Then Student Successfully registered

  Scenario: Unsuccessfully Register for a course due to Term ended
    Given Client in Student menu and has courses selected
    And Client in student menu and selected register for coruse
    And University Term Ended
    And University Registration has begun
    And University Registration has not ended
    When Client enters the code of the selected course to register
    Then Student unsuccessfully registered

  Scenario: Unsuccessfully Register for a course due to registration hasnt begun
    Given Client in Student menu and has courses selected
    And Client in student menu and selected register for coruse
    And University Term has not ended
    And University Registration has not begun
    And University Registration has not ended
    When Client enters the code of the selected course to register
    Then Student unsuccessfully registered
    
  Scenario: Unsuccessfully Register for a course due to registration ended
    Given Client in Student menu and has courses selected
    And Client in student menu and selected register for coruse
    And University Term has not ended
    And University Registration has begun
    And University Registration has ended
    When Client enters the code of the selected course to register
    Then Student unsuccessfully registered
    
  Scenario: Unsuccessfully Register for a course due to unselected courses
    Given Client in Student menu and has courses selected
    And University Term has not ended
    And University Registration has begun
    And University Registration has not ended
    When Client enters the code of the selected course to register
    Then Student unsuccessfully registered