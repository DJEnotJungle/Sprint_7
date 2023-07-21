package ru.yandex.practicum.scooter.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.yandex.practicum.scooter.UrlBeforeTest;
import ru.yandex.practicum.scooter.steps.OrderSteps;
import static org.hamcrest.CoreMatchers.notNullValue;

@DisplayName("Тест на получение списка заказов")
public class OrderListTest extends UrlBeforeTest {
    OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Тест получениясписка заказов")
    public void orderGetList(){
        ValidatableResponse response = orderSteps.ordersGet();
        response.assertThat().body("orders", notNullValue()).and().statusCode(200);
    }
}
