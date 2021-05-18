package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.toptal.screening.Credentials;
import lombok.SneakyThrows;

public class LoginPopup {
  private static final String ACTIVE_POPUP_CSS_PREFIX = "aside._show ";

  private static SelenideElement popUp = $(ACTIVE_POPUP_CSS_PREFIX);
  private static SelenideElement emailInput = $(ACTIVE_POPUP_CSS_PREFIX + "input#email");
  private static SelenideElement passwordInput = $(ACTIVE_POPUP_CSS_PREFIX + "input#pass");
  private static SelenideElement loginButton = $(ACTIVE_POPUP_CSS_PREFIX + "button.action-login");
  private static SelenideElement registerButton = $(ACTIVE_POPUP_CSS_PREFIX + "a.action-register");
  private static SelenideElement errorMessage = $(ACTIVE_POPUP_CSS_PREFIX + "div.message-error");

  public static void performLogin(Credentials credentials) {
    emailInput.shouldBe(Condition.visible)
              .setValue(credentials.getEmail());

    passwordInput.shouldBe(Condition.visible)
                 .setValue(credentials.getPassword());

    loginButton.shouldBe(Condition.visible, Condition.enabled)
               .click();
  }

  public static void shouldBeVisible() {
    popUp.shouldBe(Condition.visible);
  }

  public static void shouldBeNotVisible() {
    popUp.shouldBe(Condition.not(Condition.visible));
  }

  //workaround for reCaptcha failed check on login:
  @SneakyThrows
  public static void reCaptchaWorkaroundOnLogin() {

    for (int i = 0; i < 50; i++) {
      try {
        loginButton.click();
      } catch (ElementNotFound ex) {
        Thread.sleep(Configuration.pollingInterval);
      }
      Thread.sleep(Configuration.pollingInterval);

      if (!loginButton.isDisplayed() && !errorMessage.isDisplayed()) {
        return;
      }
    }
  }

  public static void clickRegisterButton() {
    registerButton.shouldBe(Condition.visible, Condition.enabled)
                  .click();
  }
}
