package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.DriverSettings;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBase {

  @BeforeAll
  static void setUp() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    DriverSettings.configure();

  }

}
