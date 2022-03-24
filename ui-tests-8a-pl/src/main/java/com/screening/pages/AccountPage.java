package com.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class AccountPage {

  private static SelenideElement contactData = $(".box-information .box-content");

  @Step("Verify contact data")
  public static void verifyContactData(String expectedData) {
    contactData.shouldBe(Condition.visible, Condition.exactText(expectedData));
  }
}
