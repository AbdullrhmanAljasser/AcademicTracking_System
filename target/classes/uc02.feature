@tag
Feature: UC2 Clerk Logout
	Background: 
		Given Server Running
		
  @tag1
  Scenario: Client Logs out
    Given Client logged in as Clerk
    When Client in Clerk Menu selects log out
    Then Client back to greeting terminal
