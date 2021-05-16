package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.toptal.screening.Credentials;

public class LoginPopup {
  private final static String ACTIVE_POPUP_CSS_PREFIX = "aside._show ";

  private static SelenideElement emailInput = $(ACTIVE_POPUP_CSS_PREFIX + "input#email");
  private static SelenideElement passwordInput = $(ACTIVE_POPUP_CSS_PREFIX + "input#password");
  private static SelenideElement loginButton = $(ACTIVE_POPUP_CSS_PREFIX + "button.action-login");
  private static SelenideElement registerButton = $(ACTIVE_POPUP_CSS_PREFIX + "a.action-register");

  public static void performLogin(Credentials credentials) {
    emailInput.shouldBe(Condition.visible)
              .setValue(credentials.getEmail());

    passwordInput.shouldBe(Condition.visible)
                 .setValue(credentials.getPassword());

    loginButton.shouldBe(Condition.enabled)
               .click();
  }

  public static void clickRegisterButton() {
    registerButton.shouldBe(Condition.enabled)
                  .click();
  }
}
