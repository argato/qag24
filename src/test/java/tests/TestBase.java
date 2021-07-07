package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.App;
import config.Project;
import helpers.AllureAttachments;
import helpers.DriverSettings;
import helpers.DriverUtils;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBase {

  @BeforeAll
  static void setUp() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    DriverSettings.configure();
    RestAssured.baseURI = App.config.apiUrl();
    Configuration.baseUrl = App.config.webUrl();
  }

  @AfterEach
  public void addAttachments() {
    String sessionId = DriverUtils.getSessionId();

    AllureAttachments.addScreenshotAs("Last screenshot");
    AllureAttachments.addPageSource();

    AllureAttachments.addBrowserConsoleLogs();

    Selenide.closeWebDriver();

    if (Project.isVideoOn()) {
      AllureAttachments.addVideo(sessionId);
    }
  }
}
