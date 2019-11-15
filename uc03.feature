Feature: Clerk in Create course menu
	Background: 
		Given Server Running
  
  Scenario: Successfully creating a course
    Given Client in Clerk menu and selected create course
    And University Term has not ended
    And University Registration has not begun
    When Client submit course info
    Then Course is created successfully


  Scenario: Unsuccessfully creating a course due to term ending
    Given Client in Clerk menu and selected create course
    And University Term Ended
    When Client submit course info
    Then Course is not created

  Scenario: Unsuccessfully creating a course due to registration begun
    Given Client in Clerk menu and selected create course
    And University Registration has begun
    When Client submit course info
    Then Course is not created
    
  Scenario: Successfully creating a course with incorrect format of course
    Given Client in Clerk menu and selected create course
    And University Term has not ended
    And University Registration has not begun
    When Client submit course incorrect format info
    Then Client is sent back to resubmit course info