package tests;


import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import config.ProjectConfig;
import config.auth.BookStoreAuthConfigReader;
import config.web.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    public static final config.auth.AuthConfig authConfig = BookStoreAuthConfigReader.Instance.read();
    private static final WebConfig webConfig = ConfigReader.Instance.read();

    @BeforeAll
    static void preconditionsForAllTests() {
        ProjectConfig projectConfiguration = new ProjectConfig(webConfig, authConfig);
        projectConfiguration.webConfig();
        projectConfiguration.apiConfig();
    }


    @BeforeEach
    void preconditionsForEachTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();

    }
}