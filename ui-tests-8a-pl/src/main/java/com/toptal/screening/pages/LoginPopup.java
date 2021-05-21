package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.toptal.screening.CustomerData;
import io.qameta.allure.Step;
import lombok.SneakyThrows;

public class LoginPopup {

  private static SelenideElement popUp = $("aside._show ");
  private static SelenideElement emailInput = popUp.$("input#email");
  private static SelenideElement passwordInput = popUp.$("input#pass");
  private static SelenideElement loginButton = popUp.$("button.action-login");
  private static SelenideElement registerButton = popUp.$("a.action-register");
  private static SelenideElement errorMessage = popUp.$("div.message-error");

  @Step("Perform login for user {credentials}")
  public static void performLogin(CustomerData credentials) {
    emailInput.shouldBe(Condition.visible)
              .setValue(credentials.getEmail());

    passwordInput.shouldBe(Condition.visible)
                 .setValue(credentials.getPassword());

    loginButton.shouldBe(Condition.visible, Condition.enabled)
               .click();
  }

  @Step("Login popup should be opened")
  public static void shouldBeVisible() {
    popUp.shouldBe(Condition.visible);
  }

  public static void shouldBeNotVisible() {
    popUp.shouldBe(Condition.not(Condition.visible));
  }

  //workaround for reCaptcha failed check on login:
  @SneakyThrows
  @Step("Recaptcha workaround: click login many times")
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

  @Step("Click register button")
  public static void clickRegisterButton() {
    registerButton.shouldBe(Condition.visible, Condition.enabled)
                  .click();
  }
}
