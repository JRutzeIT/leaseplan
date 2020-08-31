Feature: Endpoints belonging to Pet

  Scenario: adding a new pet to the petstore
    Given I want to add a Shiba to the petstore
    When I add the pet to the store
    Then The pet can be found in the store

  Scenario: Uploading an image with metadata to the petstore
    Given A Shiba exists in the petstore
    And I have a picture of the pet that i want to upload to the petstore
    When I upload the picture including dog as additional metadata
    Then The picture is uploaded succesfully

  Scenario: Uploading an image to the petstore
    Given A Shiba exists in the petstore
    When I upload the picture
    Then The picture is uploaded succesfully

  Scenario: Failing to upload an image to the petstore
    Given A Shiba exists in the petstore
    When I upload the picture with an incorrect id
    Then The picture is not uploaded succesfully


