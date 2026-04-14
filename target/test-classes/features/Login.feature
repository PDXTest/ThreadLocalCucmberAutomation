Feature: Login


Scenario: #1 User Login to the application

	Given User is on login page
	When User enter username as "Admin" and password as "admin123" and click on login button
	Then User navigate to the dashboard

Scenario: #2 This is second scenario

	Given User is on login page
	When User enter username as "Admin" and password as "admin123" and click on login button
	Then User navigate to the dashboard