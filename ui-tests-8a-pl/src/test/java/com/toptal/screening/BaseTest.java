package com.toptal.screening;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

  public static final String DEFAULT_SEARCH_VALUE_GLASSES = "okulary";
  public static final String DEFAULT_SEARCH_VALUE_SHOVEL = "Shovel";


  @BeforeAll
  public static void setUpAllure() {
    SelenideLogger.addListener("allure", new AllureSelenide());
  }


  @BeforeEach
  public void setUp() {
    Configuration.timeout = 10000;
    Configuration.startMaximized = true;
    WebDriverRunner.clearBrowserCache();
    open(Url.BASE_URL);
  }
}
