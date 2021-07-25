package Order;

import dto.GetOrder;
import dto.NewOrder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import services.OrderApi;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetOrderTest {
    private OrderApi getOrderApi = new OrderApi();


    //Получаем созданный нами заказ по id (Престепом нужно создать юзера с id "10")
    @Test
    public void checkGetOrder() {
//            RestAssured.
//                    when().get("https://petstore.swagger.io/v2/store/order/10").
//                    then().assertThat().statusCode(200);

        Response response = getOrderApi.getOrderById();

        response
                .then()
                .log().all()
                .body("id", equalTo(10))
                .statusCode(HttpStatus.SC_OK);

        }
    // Пробуем получить заказ без указания id
    @Test
    public void checkGetOrderFail() {
//        RestAssured.
//                when().get("https://petstore.swagger.io/v2/store/order/").
//                then().assertThat().statusCode(405);

        Response response = getOrderApi.getOrderByIdFail();

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
//                .body("type", equalTo("unknown"));

    }
    // Пробуем получить несуществующий заказ
    @Test
    public void checkGetOrderNonExisting() {
        Response response = getOrderApi.getOrderByIdNonExisting();

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("type", equalTo("error"))
                .body("message", equalTo("Order not found"))
                .body("code", equalTo(1));
    }

}
