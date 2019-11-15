@tag
Feature: UC9 Student Logout
	Background: 
		Given Server Running
		
  @tag1
  Scenario: Client Logs out
    Given Client logged in as Student
    When Client in Student Menu selects log out
    Then Client back to greeting terminal
