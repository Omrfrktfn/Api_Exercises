package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_GetRequest {

/*
https://jsonplaceholder.typicode.com/guide//44 url'ine bir get request yolladigimzda donen
response'nin

   status code 200
   contente type json
   response body'de bulunan userid'nin 5
   ve tittle'nin "optio dolar masletias sit"
   oldugunu test edin

 */

    @Test
    public void get01() {

        //request url ve body olusturma
        String url = "https://jsonplaceholder.typicode.com/posts/44";

        //expected data olusturma
        JSONObject expBody = new JSONObject();
        expBody.put("userId", 5);
        expBody.put("title", "optio dolor molestias sit");

        // System.out.println(expBody);

        //responsu kaydet
        Response response = given()
                .when()
                .get(url);
        response.prettyPrint();

        System.out.println("***********************************");

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

        JsonPath actBody = response.jsonPath();
        Assert.assertEquals(expBody.get("userId"), actBody.get("userId"));
        Assert.assertEquals(expBody.get("title"), actBody.get("title"));


    }


}
