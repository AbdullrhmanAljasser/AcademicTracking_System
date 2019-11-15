Feature: UC8 Client logs into student
	Background: 
		Given Server Running
		
  @tag1
  Scenario: Client successfully logs in as student
    Given Student has been created by Clerk
    And Client in Student login
    When Client input correct Student info
    Then Client logs in to student menu

  Scenario: Client unsuccessfully logs in as student
    Given Student has been created by Clerk
    And Client in Student login
    When Client input incorrect Student info
    Then Client is prompted for login again