package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_PostRequest {


    @Test
    public void post01() {

        String url = "https://jsonplaceholder.typicode.com/posts";

        JSONObject reqBody = new JSONObject();
        reqBody.put("title", "API");
        reqBody.put("body", "API orenmek ne guzel");
        reqBody.put("userId", "10");

        //expected data olusturmak

        JSONObject expectedBody = new JSONObject();
        expectedBody.put("title", "API");
        expectedBody.put("body", "API orenmek ne guzel");
        expectedBody.put("userId", "10");

        //3 response kaydet
        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(expectedBody.toString()).
                post(url);

        JsonPath actBody = response.jsonPath();


        response.
                then().
                contentType(ContentType.JSON).
                statusCode(200);

        Assert.assertEquals(expectedBody.get("title"), actBody.get("title"));
        Assert.assertEquals(expectedBody.get("body"), actBody.get("body"));
        Assert.assertEquals(expectedBody.get("userId"), actBody.get("userId"));


    }

}
