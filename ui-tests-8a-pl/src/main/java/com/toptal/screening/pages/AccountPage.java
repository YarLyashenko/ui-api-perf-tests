package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class AccountPage {

  private static SelenideElement contactData = $(".box-information .box-content");

  public static String getContactData() {
    return contactData.shouldBe(Condition.visible).text();
  }
}
