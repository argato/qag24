package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.App;
import config.Project;
import helpers.AllureAttachments;
import helpers.DriverSettings;
import helpers.DriverUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBaseUI {

  @BeforeEach
  void setUp() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    Configuration.baseUrl = App.config.webUrl();
    DriverSettings.configure();
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
