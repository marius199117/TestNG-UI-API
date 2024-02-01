package API.common;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class CommonDefinitions {
    public static Response sendRequest(String requestType, String endpoint, String requestBody) {
        String contentType = "application/json";

        switch (requestType.toUpperCase()) {
            case "GET":
                return RestAssured.given()
                        .contentType(contentType)
                        .when()
                        .get(endpoint)
                        .then()
                        .extract()
                        .response();
            case "POST":
                return RestAssured.given()
                        .contentType(contentType)
                        .body(requestBody)
                        .when()
                        .post(endpoint)
                        .then()
                        .extract()
                        .response();
            case "PUT":
                return RestAssured.given()
                        .contentType(contentType)
                        .body(requestBody)
                        .when()
                        .put(endpoint)
                        .then()
                        .extract()
                        .response();
            case "PATCH":
                return RestAssured.given()
                        .contentType(contentType)
                        .body(requestBody)
                        .when()
                        .patch(endpoint)
                        .then()
                        .extract()
                        .response();
            case "DELETE":
                return RestAssured.given()
                        .contentType(contentType)
                        .when()
                        .delete(endpoint)
                        .then()
                        .extract()
                        .response();
            default:
                throw new IllegalArgumentException("Invalid request type");
        }
    }

    public static void validateSchema(Response response, String schemaPath) {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }


    public static String readFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateRandomName() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public static int generateRandomId() {
        // Implement logic to generate a random ID as needed
        return 1000 + (int) (Math.random() * 9000);
    }
}
