package API.steps.pet;

import API.common.CommonDefinitions;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestPet extends CommonDefinitions {
    public void getPetRequest() {

        // Define the request body
        String requestBody = readFileContent("src/main/resources/schemas/petRequest/postRequestPetBody.json");

        // Replace placeholders with actual values
        requestBody = requestBody.replace("${randomName}", generateRandomName());
        requestBody = requestBody.replace("${randomId}", String.valueOf(generateRandomId()));

        // Send request POST
        Response postResponse = sendRequest("POST", "https://petstore.swagger.io/v2/pet", requestBody);

        // Validate response status code
        postResponse.then().assertThat().statusCode(200);

        // Parse response to verify created entity
        String createdId = postResponse.jsonPath().getString("id");
        String createdName = postResponse.jsonPath().getString("name");

        // Send request POST
        Response getResponse = sendRequest("GET", "https://petstore.swagger.io/v2/pet/" + createdId, "");

        // Validate GET response status code
        getResponse.then().assertThat().statusCode(200);

        // Validate GET response content
        getResponse.then().assertThat().body("name", equalTo(createdName));
    }
}
