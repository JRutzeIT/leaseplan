package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.io.File;
import java.util.*;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.*;

public class PetSteps {

    @Given("^I want to add a (.*) to the petstore$")
    public void iWantToAddAPetToThePetstore(
            String petName
    ) {
        Serenity.setSessionVariable("petName").to(petName);
    }

    @When("I add the pet to the store")
    public void iAddThePetToTheStore() {
        String petName = Serenity.sessionVariableCalled("petName");
        String petId = String.valueOf(Math.abs(new Random().nextInt()));
        String categoryId = String.valueOf(Math.abs(new Random().nextInt()));
        String categoryName = "dog";
        String photoUrls = "anUrlOfAPhoto";
        String tagsId = String.valueOf(Math.abs(new Random().nextInt()));
        String tagsName = "randomtag";
        String status = "available";

        Map<String, String> categoryMap = new HashMap<>();
        categoryMap.put("id", categoryId);
        categoryMap.put("name", categoryName);

        List<String> photoUrlsList = new ArrayList<String>();
        photoUrlsList.add(photoUrls);

        Map<String, String> tagsMap = new HashMap<>();
        tagsMap.put("id", tagsId);
        tagsMap.put("name", tagsName);

        List<Map> tagsList = new ArrayList<Map>();
        tagsList.add(tagsMap);

        Serenity.setSessionVariable("petId").to(petId);
        Serenity.setSessionVariable("categoryId").to(categoryId);
        Serenity.setSessionVariable("categoryName").to(categoryName);
        Serenity.setSessionVariable("photoUrls").to(photoUrlsList);
        Serenity.setSessionVariable("tags.id").to(tagsId);
        Serenity.setSessionVariable("tags.name").to(tagsName);
        Serenity.setSessionVariable("status").to(status);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", petId);
        jsonAsMap.put("category", categoryMap);
        jsonAsMap.put("name", petName);
        jsonAsMap.put("photoUrls", photoUrlsList);
        jsonAsMap.put("tags", tagsList);
        jsonAsMap.put("status", status);

        given().accept("application/json")
                .contentType("application/json")
                .body(jsonAsMap).log().body()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .when().post()
                .then().statusCode(200);
    }

    @Then("The pet can be found in the store")
    public void thePetCanBeFoundInTheStore() {
        int petId = Integer.parseInt(Serenity.sessionVariableCalled("petId"));
        int categoryId = Integer.parseInt(Serenity.sessionVariableCalled("categoryId"));
        String categoryName = Serenity.sessionVariableCalled("categoryName");
        String petName = Serenity.sessionVariableCalled("petName");
        List<String> photoUrlsList =Serenity.sessionVariableCalled("photoUrls");
        int tagsId = Integer.parseInt(Serenity.sessionVariableCalled("tags.id"));
        String tagsName = Serenity.sessionVariableCalled("tags.name");

        String status = Serenity.sessionVariableCalled("status");

        given().accept("application/json")
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet/"+petId)
                .when().get()
                .then().assertThat()
                .statusCode(200)
                .body(
                    "id", equalTo(petId),
                    "category.id", equalTo(categoryId),
                        "category.name", equalTo(categoryName),
                        "name", equalTo(petName),
                    "photoUrls", equalTo(photoUrlsList),
                    "tags.id", hasItem(tagsId),
                    "tags.name", hasItem(tagsName),
                    "status", equalTo(status)
                );
    }

    @Given("^A (.*) exists in the petstore$")
    public void APetExistsInThePetstore(
            String petName
    ) {
        Serenity.setSessionVariable("petName").to(petName);
        String petId = String.valueOf(Math.abs(new Random().nextInt()));
        String categoryId = String.valueOf(Math.abs(new Random().nextInt()));
        String categoryName = "dog";
        String photoUrls = "anUrlOfAPhoto";
        String tagsId = String.valueOf(Math.abs(new Random().nextInt()));
        String tagsName = "randomtag";
        String status = "available";

        Map<String, String> categoryMap = new HashMap<>();
        categoryMap.put("id", categoryId);
        categoryMap.put("name", categoryName);

        List<String> photoUrlsList = new ArrayList<String>();
        photoUrlsList.add(photoUrls);

        Map<String, String> tagsMap = new HashMap<>();
        tagsMap.put("id", tagsId);
        tagsMap.put("name", tagsName);

        List<Map> tagsList = new ArrayList<Map>();
        tagsList.add(tagsMap);

        Serenity.setSessionVariable("petId").to(petId);
        Serenity.setSessionVariable("categoryId").to(categoryId);
        Serenity.setSessionVariable("categoryName").to(categoryName);
        Serenity.setSessionVariable("photoUrls").to(photoUrlsList);
        Serenity.setSessionVariable("tags.id").to(tagsId);
        Serenity.setSessionVariable("tags.name").to(tagsName);
        Serenity.setSessionVariable("status").to(status);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", petId);
        jsonAsMap.put("category", categoryMap);
        jsonAsMap.put("name", petName);
        jsonAsMap.put("photoUrls", photoUrlsList);
        jsonAsMap.put("tags", tagsList);
        jsonAsMap.put("status", status);

        given().accept("application/json")
                .contentType("application/json")
                .body(jsonAsMap).log().body()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .when().post()
                .then().statusCode(200);
    }

    @Given("^I have a picture of the pet that i want to upload to the petstore$")
    public void iHaveAPictureOfThePetThatIWantToUploadToThePetstore() {
        String pathOfPicture = "upload_files/";
        String fileName = "2-drontal-dog-flavour-1110.png";
        Serenity.setSessionVariable("pathOfPicture").to(pathOfPicture);
        Serenity.setSessionVariable("fileName").to(fileName);
    }

    @When("^I upload the picture including (.*) as additional metadata$")
    public void iUploadThePictureIncludingAdditionalMetadataAsAdditionalMetadata(
            String additionalMetadata
    ) {
        String petId = Serenity.sessionVariableCalled("petId");
        String pathOfPicture = Serenity.sessionVariableCalled("pathOfPicture");
        String fileName = Serenity.sessionVariableCalled("fileName");
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("petId", petId);
        jsonAsMap.put("additionalMetadata", additionalMetadata);
        jsonAsMap.put("file", "@"+fileName+";type=image/png");

        Response response = given().contentType("application/json")
                .body(jsonAsMap).log().body()
                .contentType("multipart/form-data")
                .multiPart(new File(pathOfPicture+fileName))
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet/"+petId+"/uploadImage")
                .when().post()
                .thenReturn();

        Serenity.setSessionVariable("response").to(response);
    }

    @Then("The picture is uploaded succesfully")
    public void thePictureIsUploadedSuccesfully() {
        Response response = Serenity.sessionVariableCalled("response");

        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("type", equalTo("unknown"),
                        "message", containsString("File uploaded"));
    }

    @When("I upload the picture")
    public void iUploadThePicture() {
        String petId = Serenity.sessionVariableCalled("petId");
        String pathOfPicture = "upload_files/";
        String fileName = "2-drontal-dog-flavour-1110.png";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("petId", petId);
        jsonAsMap.put("file", "@"+fileName+";type=image/png");

        Response response = given().contentType("application/json")
                .body(jsonAsMap).log().body()
                .contentType("multipart/form-data")
                .multiPart(new File(pathOfPicture+fileName))
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet/"+petId+"/uploadImage")
                .when().post()
                .thenReturn();

        Serenity.setSessionVariable("response").to(response);
    }

    @When("I upload the picture with an incorrect id")
    public void iUploadThePictureWithAnIncorrectId() {
        String petId = "thesearentnumbers";
        String pathOfPicture = "upload_files/";
        String fileName = "2-drontal-dog-flavour-1110.png";
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("petId", petId);
        jsonAsMap.put("file", "@"+fileName+";type=image/png");

        Response response = given().contentType("application/json")
                .body(jsonAsMap).log().body()
                .contentType("multipart/form-data")
                .multiPart(new File(pathOfPicture+fileName))
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet/"+petId+"/uploadImage")
                .when().post()
                .thenReturn();

        Serenity.setSessionVariable("response").to(response);
    }

    @Then("The picture is not uploaded succesfully")
    public void thePictureIsNotUploadedSuccesfully() {
        Response response = Serenity.sessionVariableCalled("response");
        response.then().assertThat().body("code", not("200"));
    }


}