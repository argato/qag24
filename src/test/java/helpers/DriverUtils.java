package helpers;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.Logging.LOGGER;
import static org.openqa.selenium.logging.LogType.BROWSER;

import com.codeborne.selenide.Selenide;
import config.Project;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverUtils {

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid", "");

    }

    public static byte[] getScreenshotAsBytes() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static byte[] getPageSourceAsBytes() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    public static URL getVideoUrl(String sessionId) {
        String videoUrl = Project.config.videoStorage() + sessionId + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            LOGGER.warn("[ALLURE VIDEO ATTACHMENT ERROR] Wrong test video url, {}", videoUrl);
            e.printStackTrace();
        }
        return null;
    }

    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }
}
