package tests.web;

import com.codeborne.selenide.Selenide;
import config.Project;
import helpers.AllureAttachments;
import helpers.DriverUtils;
import org.junit.jupiter.api.AfterEach;
import tests.TestBase;

public class TestBaseUI extends TestBase {

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
