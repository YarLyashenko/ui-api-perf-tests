package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class RemoveFromCartConfirmationPopup {
  private static SelenideElement popUp = $("aside.confirm._show ");
  private static SelenideElement dismissButton = popUp.$("button.action-dismiss");
  private static SelenideElement acceptButton = popUp.$("button.action-accept");

  public static void accept() {
    acceptButton.shouldBe(Condition.visible, Condition.enabled).click();
  }

  public static void dismiss() {
    dismissButton.shouldBe(Condition.visible, Condition.enabled).click();
  }
}
