@tag
Feature: Sequece testing

  @tag1
  Scenario: Sequence one
    Given ClerkOne logged in
    And ClerkOne Creates StudentOne
    And ClerkOne logs out
    And StudentOne logged in
    And ClerkTwo logged in
    And ClerkTwo Create CourseOne
    When StudentTwo Register in CourseOne
    Then StudentOne enrolls in CourseOne and logsout

  @tag2
  Scenario: Sequence two
    Given ClerkTwo logs in
    And ClerkTwo Creates CourseTwo
    And ClerkTwo Creates CourseThree
    And ClerkTwo Creates CourseFour
    And ClerkTwo Creates CourseFive
    And ClerkTwo delete CourseThree
    And ClerkThree logged in
    And ClerkThree creates StudentTwo
    And ClerkThree creates StudentThree
    And ClerkThree delets StudentThree
    When StudentTwo logged in
    And StudentTwo Register CourseTwo
    And StudentTwo Register CourseFive
    And StudentTwo deRegister CourseFive
    Then StudentTwo logs out
    
  Scenario: Sequence three
    Given ClerkTwo logged in
    And ClerkTwo Creates CourseFour
    When Registration time Ends
    Then ClerkTwo cancel course
    
