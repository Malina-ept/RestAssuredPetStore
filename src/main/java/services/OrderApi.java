package services;

import dto.GetOrder;
import dto.NewOrder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class OrderApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/";
    private RequestSpecification spec;
    private static final String ORDER = "/store/order";

    public OrderApi() {
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public Response createOrder(NewOrder order) {
        return
                given(spec)
                        .with()
                        .body(order)
                        .log().all()
                        .when()
                        .post(ORDER);
    }

    public Response getOrderById() {

        return
                given(spec)
                        .with()
                        .log().all()
                        .when()
                        .get("/store/order/10");


    }
    public Response getOrderByIdFail() {

        return
                given(spec)
                        .with()
                        .log().all()
                        .when()
                        .get("/store/order/");


    }

    public Response getOrderByIdNonExisting() {

        return
                given(spec)
                        .with()
                        .log().all()
                        .when()
                        .get("/store/order/123456789");


    }
}
