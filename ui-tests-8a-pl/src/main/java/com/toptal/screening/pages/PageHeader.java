package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PageHeader {

  private static SelenideElement searchInput = $("input#search");
  private static SelenideElement searchButton = $("button.action.search");
  private static SelenideElement loginButton = $(".page-header .login-link a");

  public static void clickLogin() {
    loginButton.shouldBe(Condition.enabled);
    loginButton.click();
  }
}
