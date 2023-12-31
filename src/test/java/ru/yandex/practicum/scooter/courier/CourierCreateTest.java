package ru.yandex.practicum.scooter.courier;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.practicum.scooter.UrlBeforeTest;
import ru.yandex.practicum.scooter.jsonclass.Courier;
import ru.yandex.practicum.scooter.steps.CourierSteps;
import static org.hamcrest.CoreMatchers.equalTo;


@DisplayName("Тесты создания курьеров")
public class CourierCreateTest extends UrlBeforeTest {
    CourierSteps courierSteps = new CourierSteps();
    Courier courier = new Courier("testing124", "testing4322", "karl124");
    Courier courierId = new Courier("testing124", "testing4322");
    Courier courierNonData = new Courier("testing124", "", "karl124");
    int id;

    @Test
    @DisplayName("Тест создания курьера со всеми обязательными полями")
    public void testCreateCourierIdeal(){
        ValidatableResponse response = courierSteps.courierCreate(courier);
        id=courierSteps.courierGetId(courierId).extract().path("id");
        response.assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Тест создания двух одинкаовых курьеров / Тест создания курьеров с одинаковыми логинами")
    public void testCreateDuplicateCourierIdeal(){
        ValidatableResponse response = courierSteps.courierCreate(courier);
        ValidatableResponse responseDuplicate = courierSteps.courierDuplicateCreate(courier);
        id=courierSteps.courierGetId(courierId).extract().path("id");
        responseDuplicate.assertThat().statusCode(409);
    }

    @Test
    @DisplayName("Тест создания курьера с неполными данными")
    public void testCreateCourierWithIncompleteData(){
        ValidatableResponse response = courierSteps.createCourierWithIncompleteData(courierNonData);
        response.assertThat().statusCode(400);
    }

    @After
    @Step("Проверяем надо ли удалить курьера")
    public void deleteCourier(){
        if (id != 0) {
            courierSteps.courierDelete(id);
        }
    }

}
