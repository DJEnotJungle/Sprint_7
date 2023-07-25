package ru.yandex.practicum.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.scooter.jsonclass.Order;
import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.scooter.constants.Constants.*;

public class OrderSteps {
    @Step("Создание заказа")
    public ValidatableResponse orderCreate(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDER_CREATE_PATH)
                .then();
    }

    @Step("Получение списка всех заказов")
    public ValidatableResponse ordersGet() {
        return given()
                .when()
                .get(ORDERS_GET_PATH)
                .then();
    }

    @Step("Отмена заказа по трек-номеру")
    public ValidatableResponse orderChancel(int track) {
        return given()
                .header("Content-type", "application/json")
                .body(track)
                .when()
                .put(ORDER_CHANCEL_PATH)
                .then();
    }
}
