package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.toptal.screening.Constants;
import com.toptal.screening.CustomerData;
import io.qameta.allure.Step;

public class RegisterPage {

  private static SelenideElement firstnameInput = $("input#firstname");
  private static SelenideElement lastnameInput = $("input#lastname");
  private static SelenideElement emailInput = $("input#email_address");
  private static SelenideElement passwordInput = $("input#password");
  private static SelenideElement acceptTermsCheckbox = $("input#accept_terms");
  private static SelenideElement acceptTermsLabel = $(".accept_terms label");
  private static SelenideElement newsletterCheckbox = $("input#is_subscribed");
  private static SelenideElement newsletterLabel = $(".newsletter label");
  private static SelenideElement submitButton = $("button.submit");

  public static void registerUser(CustomerData credentials) {
    registerUser(credentials, false);
  }

  @Step("Perform registration for user {credentials} with newsletter:{withNewsletter}")
  public static void registerUser(CustomerData credentials, boolean withNewsletter) {
    firstnameInput.shouldBe(Condition.visible)
                  .scrollTo()
                  .setValue(credentials.getFirstname());

    lastnameInput.shouldBe(Condition.visible)
                 .setValue(credentials.getLastname());

    emailInput.shouldBe(Condition.visible)
              .setValue(credentials.getEmail());

    passwordInput.shouldBe(Condition.visible)
                 .setValue(credentials.getPassword());

    acceptTermsLabel.shouldBe(Condition.visible)
                    .click();
    acceptTermsCheckbox.shouldBe(Condition.checked);

    if (withNewsletter) {
      newsletterLabel.shouldBe(Condition.visible)
                     .scrollTo()
                     .click();
    } else {
      newsletterCheckbox.shouldBe(Condition.not(Condition.checked));
    }

    submitButton.scrollIntoView(Constants.SCROLL_OPTIONS)
                .shouldBe(Condition.visible, Condition.enabled)
                .click();
  }
}
