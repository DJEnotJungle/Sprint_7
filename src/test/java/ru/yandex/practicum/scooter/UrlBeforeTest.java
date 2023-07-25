package ru.yandex.practicum.scooter;

import io.restassured.RestAssured;
import org.junit.Before;
import static ru.yandex.practicum.scooter.constants.Constants.MASTER_URL;

public class UrlBeforeTest {
    @Before
    public void setURL(){
        RestAssured.baseURI=MASTER_URL;
    }
}
