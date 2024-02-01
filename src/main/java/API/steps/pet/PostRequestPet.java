package API.steps.pet;

import API.common.CommonDefinitions;
import io.restassured.response.Response;

public class PostRequestPet extends CommonDefinitions {
    public void postPetRequest() {

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
        postResponse.jsonPath().getString("id");
        postResponse.jsonPath().getString("name");

        //  Validate schema for the response body
        String postResponseSchema = "schemas/petRequest/postResponseSchema.json";
        validateSchema(postResponse, postResponseSchema);
    }
}