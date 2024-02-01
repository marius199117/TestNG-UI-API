package API_Test_Cases;

import API.common.CommonDefinitions;
import API.steps.pet.GetRequestPet;
import API.steps.pet.PostRequestPet;
import org.testng.annotations.Test;


public class PetRequest extends CommonDefinitions {
    PostRequestPet postRequestPet = new PostRequestPet();
    GetRequestPet getRequestPet = new GetRequestPet();

    @Test
    public void testPostRequest() {
        postRequestPet.postPetRequest();
    }

    @Test
    public void testGetRequest() {
        getRequestPet.getPetRequest();
    }

}