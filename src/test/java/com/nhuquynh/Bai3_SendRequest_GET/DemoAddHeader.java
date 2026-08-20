package com.nhuquynh.Bai3_SendRequest_GET;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoAddHeader{
    @Test
    public void testAddHeader(){
        //Khai báo đối tượng request
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/users");
        //Add header theo yêu cầu, với cú pháp (key, vale)
        request.header("accept","application/json");
        //request.accept("application/json")

        Response response = request.when().get();
        //In giá trị
        response.prettyPrint();
        //Kiểm tra
        response.then().statusCode(200);
    }
}
