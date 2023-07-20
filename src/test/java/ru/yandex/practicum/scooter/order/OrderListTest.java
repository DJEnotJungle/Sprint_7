package ru.yandex.practicum.scooter.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.steps.OrderSteps;

@DisplayName("Тест на получение списка заказов")
public class OrderListTest {
    OrderSteps orderSteps = new OrderSteps();

    @Before
    public void setURL(){
        RestAssured.baseURI="http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Тест получениясписка заказов")
    public void orderGetList(){
        ValidatableResponse response = orderSteps.ordersGet();
        response.assertThat().statusCode(200);
    }
}
