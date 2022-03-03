Feature: Users publish and retrieve prescriptions

  Scenario: Successful Post request publishes prescriptions
    When a post request is made to /demo/addPrescription
    Then the response will be saved
    And I should get back a status code of 200

