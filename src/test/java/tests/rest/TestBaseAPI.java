package tests.rest;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.App;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBaseAPI {

  @BeforeAll
  static void setUp() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    RestAssured.baseURI = App.config.apiUrl();

  }

}
