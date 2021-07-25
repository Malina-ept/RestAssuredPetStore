package Order;

import dto.NewOrder;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.OrderApi;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CreateOrderTest {

    private OrderApi orderApi = new OrderApi();

    //Новый заказ создается с полями из запроса
    @Test
    public void checkOrder() {
        NewOrder order = NewOrder.builder()
                .Status("new")
                .Complete(true)
                .Id(10L)
                .PetId(222L)
                .Quantity(3L)
                .ShipDate("2021-07-24")
                .build();


        Response response = orderApi.createOrder(order);


        response
                .then()
                .log().all()
                .body("id", equalTo(10))
                .body("status", equalTo("new"))
                .body("petId", equalTo(222))
                .body("quantity", equalTo(3))
                .body("shipDate", equalTo("2021-07-24T00:00:00.000+0000"))
                .body("complete", equalTo(true))


                .statusCode(HttpStatus.SC_OK);
    }

    //Если формат даты заказа не соответствует ожидаемому, то получим 500 Server Error
    @Test
    public void checkOrderFail() {
        NewOrder order = NewOrder.builder()
                .Status("new")
                .Complete(true)
                .Id(10L)
                .PetId(222L)
                .Quantity(3L)
                .ShipDate("01.01.2021")
                .build();


        Response response = orderApi.createOrder(order);


        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }


}
