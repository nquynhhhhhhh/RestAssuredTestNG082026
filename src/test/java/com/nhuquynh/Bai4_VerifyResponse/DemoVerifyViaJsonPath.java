package com.nhuquynh.Bai4_VerifyResponse;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoVerifyViaJsonPath {
    @Test
    public void testVerifyResponseUseAssertTestNG() {
        RequestSpecification request = given();
        request.baseUri("https://book.anhtester.com/api")
                .accept("application/json");

        String id = "cmji2u222002l7uk1tm5inbnh"; //ID của book. Gắn vào sau path url luôn

        Response response = request.when().get("/book/" + id);
        response.prettyPrint();


        //Verify kết quả từ response với Assert trong TestNG
        //Dùng class Assert chấm gọi 2 hàm chính là assertEquals và assertTrue
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code chưa đúng.");
        Assert.assertEquals(response.getContentType(), "application/json", "Content Type chưa đúng.");

        //Muốn lấy giá trị từng key trong JSON body để compare chính xác thì dùng JsonPath
        JsonPath jsonPath = response.jsonPath(); //Lưu hết body vào đối tượng jsonPath

        //Truy xuất giá trị theo key hoặc đường dẫn xpath theo cấp bậc
        String name = jsonPath.get("name");
        System.out.println("Name: " + name);
        //Dùng Assert của TestNG để verify
        //Assert.assertEquals(name.contains("Kiên trì"), true, "Name không tồn tại."); => đang so sánh chứa
        Assert.assertEquals(name, "Kiên trì sẽ thành công", "Name không tồn tại.");

        //Khi lấy trực tiếp giá trị từ jsonPath thì cần toString và phải chuyển số về sạng chuỗi để so sánh
        //Assert.assertEquals(Integer.parseInt(jsonPath.get("price").toString()), 50000, "Price không đúng.");
        Assert.assertEquals(jsonPath.get("price").toString(), "50000", "Price không đúng.");

        //Lấy đường dẫn path trong mảng của object "image"
        //Index bắt đầu tính từ 0
        String imagePath = jsonPath.get("picture[0].path");
        System.out.println(imagePath);
        Assert.assertTrue(imagePath.contains("kien-tri-se-thanh-cong"), "Không đúng hình ảnh.");
        //Assert.assertTrue(jsonPath.get("picture[0].path").toString().contains("kien-tri-se-thanh-cong"), "Không đúng hình ảnh thứ 2.");
        //=> nếu kh tạo biến thì phải thêm toString để nó biết kiểu dữ liệu
    }
}
