package ru.yandex.practicum.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.scooter.jsonclass.Courier;
import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.scooter.constants.Constants.*;

public class CourierSteps {

    @Step("Создание курьера")
    public ValidatableResponse courierCreate(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Создание дублирующего курьера")
    public ValidatableResponse courierDuplicateCreate(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Создание дублирующего курьера")
    public ValidatableResponse createCourierWithIncompleteData(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Удаление курьера")
    public void courierDelete(int id){
        given()
                .delete(COURIER_PATH+ "/" + id)
                .then();
    }

    @Step("Логин курьера")
    public ValidatableResponse courierLogin(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then();
    }
    @Step("Получение ID для удаления")
    public ValidatableResponse courierGetId(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_LOGIN_PATH)
                .then();
    }
}
