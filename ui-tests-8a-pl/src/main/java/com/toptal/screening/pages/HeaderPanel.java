package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class HeaderPanel {

  private static SelenideElement searchInput = $("input#search");
  private static SelenideElement searchButton = $("button.action.search");
  private static SelenideElement loginButton = $(".page-header .login-link a");
  private static SelenideElement logoutButton = $(".page-header a.logout-link");
  private static SelenideElement userName = $(".page-header .customer-name-text");
  private static SelenideElement cartPopupButton = $("a.action.showcart");
  private static SelenideElement allowCookies = $("#btn-cookie-allow");

  @Step("Click login")
  public static void clickLogin() {
    loginButton.shouldBe(Condition.visible, Condition.enabled)
               .scrollTo()
               .click();
  }

  @Step("Allow cookies")
  public static void clickAllowCookiesButton() {
    allowCookies.shouldBe(Condition.visible, Condition.enabled)
                .click();
  }

  @Step("Click logout")
  public static void clickLogout() {
    logoutButton.shouldBe(Condition.visible, Condition.enabled)
                .scrollTo()
                .click();
  }

  @Step("Open mini cart")
  public static void openCartPopup() {
    cartPopupButton.shouldBe(Condition.visible, Condition.enabled)
                   .scrollTo()
                   .click();
  }

  public static String getUserName() {
    return userName.shouldBe(Condition.visible).text();
  }

  @Step("Perform product search by {input}")
  public static void searchBy(String input) {

    searchInput.shouldBe(Condition.visible, Condition.enabled)
               .setValue(input)
               .pressEnter();

    //search button is disabled while autosuggestion list is open
    //searchButton.shouldBe(Condition.visible).click();
  }
}
