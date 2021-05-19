package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PageHeader {

  private static SelenideElement searchInput = $("input#search");
  private static SelenideElement searchButton = $("button.action.search");
  private static SelenideElement loginButton = $(".page-header .login-link a");
  private static SelenideElement logoutButton = $(".page-header a.logout-link");
  private static SelenideElement userName = $(".page-header .customer-name-text");

  public static void clickLogin() {
    loginButton.shouldBe(Condition.visible, Condition.enabled)
               .click();
  }

  public static void clickLogout() {
    logoutButton.shouldBe(Condition.visible, Condition.enabled)
                .click();
  }

  public static String getUserName() {
    return userName.shouldBe(Condition.visible).text();
  }

  public static void searchBy(String input) {

    searchInput.shouldBe(Condition.visible, Condition.enabled)
               .setValue(input)
               .pressEnter();

    //search button is disabled while autosuggestion list is open
    //searchButton.shouldBe(Condition.visible).click();
  }
}
