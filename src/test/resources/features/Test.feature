Feature: Users publish and retrieve prescriptions

  Scenario Outline: Successful Post request to add user
    Given The user provides username "<userName>" and email "<email>"
    When a post request is made to "<basePath>" "<endPoint>"
    Then the response will be saved
    And I should get back a status code of 200
    Examples:
      | basePath | endPoint | userName | email          |
      | demo     | add      | Test     | test@gmail.com |
