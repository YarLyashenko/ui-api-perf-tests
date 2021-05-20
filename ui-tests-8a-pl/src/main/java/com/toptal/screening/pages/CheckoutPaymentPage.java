package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.toptal.screening.Constants;
import io.qameta.allure.Step;

public class CheckoutPaymentPage {
  private static SelenideElement submitOrderButton =
      $("#checkout-payment-method-load button[type='submit']");
  private static SelenideElement shippingInfo = $(".ship-to .shipping-information-content");

  @Step("Click on button to submit order")
  public static void clickSubmitOrderButton() {
    submitOrderButton.scrollIntoView(Constants.SCROLL_OPTIONS)
                     .shouldBe(Condition.visible, Condition.enabled)
                     .click();
  }

  public static String getShippingInfo() {
    return shippingInfo.shouldBe(Condition.visible).text();
  }
}
