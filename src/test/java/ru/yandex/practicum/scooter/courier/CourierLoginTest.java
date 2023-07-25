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
import static org.hamcrest.Matchers.greaterThan;

@DisplayName("Тесты авторизации курьеров")
public class CourierLoginTest extends UrlBeforeTest {
    CourierSteps courierSteps = new CourierSteps();

    Courier courier = new Courier("test678", "test678", "test678");
    Courier courierLogin = new Courier("test678", "test678");
    Courier courierLoginWithOutPassword = new Courier("test678", "");
    Courier courierLoginWithOutLogin = new Courier("", "test678");
    Courier courierLoginWithErrorLogin = new Courier("test678d", "test678");
    Courier courierLoginWithErrorPassword = new Courier("test678", "test678d");

    int id;

    @Test
    @DisplayName("Тест авторизации курьеров со всеми верными данными")
    public void testLoginCourierIdeal(){
        ValidatableResponse response = courierSteps.courierCreate(courier);
        ValidatableResponse responseLogin = courierSteps.courierLogin(courierLogin);
        id=courierSteps.courierGetId(courierLogin).extract().path("id");
        responseLogin.assertThat().body("id", greaterThan(0)).and().statusCode(200);
    }

    @Test
    @DisplayName("Тест авторизации курьеров со всеми верными данными,но без регистрации")
    public void testLoginCourierWithoutCreating(){
        ValidatableResponse responseLogin = courierSteps.courierLogin(courierLogin);
        responseLogin.assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Test
    @DisplayName("Тест авторизации курьеров без пароля")
    public void testLoginCourierWithoutPassword() {
        ValidatableResponse response = courierSteps.courierCreate(courier);
        ValidatableResponse responseLoginWithOutPassword = courierSteps.courierLogin(courierLoginWithOutPassword);
        id = courierSteps.courierGetId(courierLogin).extract().path("id");
        responseLoginWithOutPassword.assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }

    @Test
    @DisplayName("Тест авторизации курьеров без логина")
    public void testLoginCourierWithoutLogin(){
        ValidatableResponse response = courierSteps.courierCreate(courier);
        ValidatableResponse responseLoginWithOutLogin = courierSteps.courierLogin(courierLoginWithOutLogin);
        id=courierSteps.courierGetId(courierLogin).extract().path("id");
        responseLoginWithOutLogin.assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }

    @Test
    @DisplayName("Тест авторизации курьеров с опечаткой в логине")
    public void testLoginCourierWithErrorLogin(){
        ValidatableResponse response = courierSteps.courierCreate(courier);
        ValidatableResponse responseLoginWithErrorLogin = courierSteps.courierLogin(courierLoginWithErrorLogin);
        id=courierSteps.courierGetId(courierLogin).extract().path("id");
        responseLoginWithErrorLogin.assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Test
    @DisplayName("Тест авторизации курьеров с опечаткой в пароле")
    public void testLoginCourierWithErrorPassword(){
        ValidatableResponse response = courierSteps.courierCreate(courier);
        ValidatableResponse responseLoginWithErrorPassword = courierSteps.courierLogin(courierLoginWithErrorPassword);
        id=courierSteps.courierGetId(courierLogin).extract().path("id");
        responseLoginWithErrorPassword.assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @After
    @Step("Проверяем надо ли удалить курьера")
    public void deleteCourier(){
        if (id != 0) {
            courierSteps.courierDelete(id);
        }
    }
}
