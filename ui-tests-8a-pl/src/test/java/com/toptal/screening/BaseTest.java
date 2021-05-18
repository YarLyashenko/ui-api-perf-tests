package com.toptal.screening;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
  public static final int SYMBOLS_COUNT = 10;

  @BeforeAll
  public static void setUpAllure() {
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  public static String generateEmail() {
    return String.format("%1$s@%1$s.com", RandomStringUtils.randomAlphanumeric(SYMBOLS_COUNT));
  }

  public static String generatePassword() {
    return String.format("%s!1I", RandomStringUtils.randomAlphanumeric(SYMBOLS_COUNT));
  }

  @BeforeEach
  public void setUp() {
    Configuration.timeout = 10000;
    Configuration.startMaximized = true;
    WebDriverRunner.clearBrowserCache();
    open(Url.BASE_URL);
  }
}
