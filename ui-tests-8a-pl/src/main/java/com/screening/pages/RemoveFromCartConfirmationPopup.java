package com.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class RemoveFromCartConfirmationPopup {
  private static SelenideElement popUp = $("aside.confirm._show ");
  private static SelenideElement dismissButton = popUp.$("button.action-dismiss");
  private static SelenideElement acceptButton = popUp.$("button.action-accept");

  @Step("Accept removing item from cart")
  public static void accept() {
    acceptButton.shouldBe(Condition.visible, Condition.enabled).click();
  }

  @Step("Dismiss removing item from cart")
  public static void dismiss() {
    dismissButton.shouldBe(Condition.visible, Condition.enabled).click();
  }

  @Step("Popup automatically closed")
  public static void shouldBeClosed() {
    popUp.shouldBe(Condition.not(Condition.visible));
  }
}
