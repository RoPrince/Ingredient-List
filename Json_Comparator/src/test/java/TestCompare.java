import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class TestCompare {

    @Test
    public void testResponses(){

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        Response response = httpRequest.get("http://t9805isp0001.target.com/ReplenishmentServices/api/BlockAisle/GetMasterBlockAisleDetails");
        ResponseBody body = response.getBody();

    }
}
