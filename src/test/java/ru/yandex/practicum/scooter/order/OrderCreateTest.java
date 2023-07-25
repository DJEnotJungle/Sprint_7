package ru.yandex.practicum.scooter.order;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.scooter.UrlBeforeTest;
import ru.yandex.practicum.scooter.jsonclass.Order;
import ru.yandex.practicum.scooter.steps.OrderSteps;
import java.util.List;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
@DisplayName("Тесты на создание заказов с разными цветами")
public class OrderCreateTest extends UrlBeforeTest {

    OrderSteps orderSteps = new OrderSteps();
    int track;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Заказ с перебором цветов: {0} {1} {2} {3}")
    public static Object[][] testData(){
        return new Object[][]{
                {"Манки Д.", "Луффи", "Деревня Фууся", "1", "+79876543210", 1, "1505-05-05", "Я — Луффи! Я стану Королём пиратов!", List.of("BLACK")},
                {"Манки Д.", "Луффи", "Деревня Фууся", "1", "+79876543210", 1, "1505-05-05", "Я — Луффи! Я стану Королём пиратов!", List.of("GREY")},
                {"Манки Д.", "Луффи", "Деревня Фууся", "1", "+79876543210", 1, "1505-05-05", "Я — Луффи! Я стану Королём пиратов!", List.of("BLACK", "GREY")},
                {"Манки Д.", "Луффи", "Деревня Фууся", "1", "+79876543210", 1, "1505-05-05", "Я — Луффи! Я стану Королём пиратов!", List.of()},
        };
    }

    @Test
    @DisplayName("Оформляем заказ")
    public void orderCreate(){
        Order order =new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = orderSteps.orderCreate(order);
        track=response.extract().path("track");
        response.assertThat().body("track", is(notNullValue())).and().statusCode(201);
    }

    @After
    @Step("Отменяем заказ")
    public void orderCancel(){
        orderSteps.orderChancel(track);
    }
}
