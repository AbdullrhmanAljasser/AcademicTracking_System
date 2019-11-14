Feature: UC1
	Background: 
		Given Server Running

  Scenario: Clerk log into ATC Successully
    Given Client in Clerk login
    When Client inputs correct Clerk password
    Then Client is logged into Clerk menu

Scenario: Clerk log into ATC with incorrect Password
    Given Client in Clerk login
    When Client inputs incorrect Clerk password
    Then Client is sent back to login menu
    