package com.nhuquynh.Bai4_VerifyResponse;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DemoVerifyViaThenMethod {
    @Test
    public void testVerifyResponseUseThenMethod() {
        RequestSpecification request = given();
        request.baseUri("https://book.anhtester.com/api")
                .accept("application/json");

        String id = "cmji2u222002l7uk1tm5inbnh"; //ID của book. Gắn vào sau path url luôn

        Response response = request.when().get("/book/" + id);
        response.prettyPrint();

        //Verify kết quả từ response với hàm then()
        response.then().statusCode(200);
        response.then().contentType("application/json");
        //Đối với body thì cần điền cấu trúc theo xpath từ json
        //Hàm equalTo thuộc thư viện org.hamcrest.Matchers
        response.then().body("name", containsString("Kiên trì"));
        response.then().body("price", equalTo(50000));
        //Dùng vị trí index để lấy thứ tự phần tử trong JSON body. Tính từ 0
        response.then().body("picture[0].path", containsString("kien-tri-se-thanh-cong"));
    }




}
