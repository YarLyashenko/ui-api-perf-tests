package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class CheckoutPaymentPage {
  private static SelenideElement submitOrderButton =
      $("#checkout-payment-method-load button[type='submit']");
  private static SelenideElement shippingInfo = $(".ship-to .shipping-information-content");

  public static void clickSubmitOrderButton() {
    submitOrderButton.shouldBe(Condition.visible, Condition.enabled)
                     .click();
  }

  public static String getShippingInfo() {
    return shippingInfo.shouldBe(Condition.visible).text();
  }
}
