package com.screening;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.screening.pages.HeaderPanel;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ScreenShooterExtension.class})
public abstract class BaseTest {

  public static final String DEFAULT_SEARCH_VALUE_GLASSES = "okulary";
  public static final String DEFAULT_SEARCH_VALUE_SHOVEL = "łopata";
//
//  static final BrowserWebDriverContainer browser;
//
//  static {
//    final ChromeOptions capabilities = new ChromeOptions();
//    capabilities.addArguments("headless")
//                .addArguments("disable-gpu")
//                .addArguments("no-sandbox")
//                .addArguments("disable-dev-shm-usage")
//                .addArguments("window-size=1920,1080");
//
//    browser = new BrowserWebDriverContainer().withCapabilities(capabilities);
//    browser.start();
//  }

  @BeforeAll
  public static void setUpDriverAndAllure() {
//    RemoteWebDriver driver = browser.getWebDriver();
//    WebDriverRunner.setWebDriver(driver);
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @BeforeEach
  @Step("Open page")
  public void setUp() {
    WebDriverRunner.clearBrowserCache();

    open(Url.BASE_URL);
    Screenshots.saveScreenshotAndPageSource();
    HeaderPanel.allowCookies();
  }
}
