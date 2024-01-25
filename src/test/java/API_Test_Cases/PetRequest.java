package API_Test_Cases;

import API.CommonDefinitions;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PetRequest extends CommonDefinitions {
    @Test
    public void testPetRequestAndSchemaValidation() {
        // Define the POST endpoint
        String postEndpoint = "https://petstore.swagger.io/v2/pet";

        // Define the request body
        String requestBodyPath = "src/main/resources/schemas/petRequest/postRequestPetBody.json";
        String requestBody = readFileContent(requestBodyPath);

        // Replace placeholders with actual values
        requestBody = requestBody.replace("${randomName}", generateRandomName());
        requestBody = requestBody.replace("${randomId}", String.valueOf(generateRandomId()));

        // Send POST request
        Response postResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(postEndpoint);

        // Validate response status code
        postResponse.then().assertThat().statusCode(200);

        // Parse response to verify created entity
        long createdId = postResponse.jsonPath().getLong("id");
        String createdName = postResponse.jsonPath().getString("name");

        //Define the GET endpoint
        String getEndpoint = "https://petstore.swagger.io/v2/pet/" + createdId;

        // Send GET request
        Response getResponse = given()
                .when()
                .get(getEndpoint);

        // Validate GET response status code
        getResponse.then().assertThat().statusCode(200);

        // Validate GET response content
        getResponse.then().assertThat().body("name", equalTo(createdName));

        // Validate schema for the response body
        String postResponseSchema = "schemas/petRequest/postResponseSchema.json";
        validateSchema(postResponse, postResponseSchema);

        // Define the DELETE endpoint
        String deleteEndpoint = "https://petstore.swagger.io/v2/pet/" + createdId;

        // Send DELETE request
        Response deleteResponse = given()
                .when()
                .delete(deleteEndpoint);

        // Validate DELETE response status code
        deleteResponse.then().assertThat().statusCode(200);

        // Validate schema for the response body
        String deleteResponseSchema = "schemas/petRequest/deleteResponseSchema.json";
        validateSchema(deleteResponse, deleteResponseSchema);
    }
}