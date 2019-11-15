Feature: UC11 Client deregister from course 

  @tag1
  Scenario: Successfully deregister from course
    Given Student registered in course to be dropped
    And Client in student menu and selected deregister course
    And University Term has not ended
    And University Registration has begun
    And University Registration has not ended
    When Client enters the code of the course to deregister
    Then Student successfully deregister from course

  Scenario: Unsuccessfully deregister from course due to term ending
    Given Student registered in course to be dropped
    And Client in student menu and selected deregister course
    And University Term Ended
    And University Registration has begun
    And University Registration has not ended
    When Client enters the code of the course to deregister
    Then Student unsuccessfully deregister from course
    
  Scenario: Unsuccessfully deregister from course due to registration hasnt begun
    Given Student registered in course to be dropped
    And Client in student menu and selected deregister course
    And University Term has not ended
    And University Registration has not begun
    And University Registration has not ended
    When Client enters the code of the course to deregister
    Then Student unsuccessfully deregister from course
    
  Scenario: Unsuccessfully deregister from course due to registration has ended
    Given Student registered in course to be dropped
    And Client in student menu and selected deregister course
    And University Term has not ended
    And University Registration has begun
    And University Registration has ended
    When Client enters the code of the course to deregister
    Then Student unsuccessfully deregister from course
    