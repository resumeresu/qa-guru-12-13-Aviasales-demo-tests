package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.RemoteCredentialsConfig;
import helpers.Attachmets;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void setUp() {
        RemoteCredentialsConfig config = ConfigFactory.create(RemoteCredentialsConfig.class);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String login = config.login();
        String password = config.password();
        String remote = System.getProperty("remote", "selenoid.autotests.cloud/wd/hub");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        Configuration.baseUrl = "https://www.aviasales.ru";
        Configuration.browserSize = browserSize;
        //Configuration.remote = "https://" + login + ":" + password + "@" + remote;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attachmets.screenshotAs("Screenshot");
        Attachmets.pageSource();
        Attachmets.browserConsoleLogs();
        Attachmets.addVideo();
        closeWebDriver();
    }
}
