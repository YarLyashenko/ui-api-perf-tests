package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProductPage {

  private static SelenideElement name = $(".page-title span");
  private static SelenideElement addToCartButton = $("button#product-addtocart-button");
  private static SelenideElement addToCartConfirmationMessage = $(".product-add-form .message ");

  public static String getName() {
    return name.shouldBe(Condition.visible).text();
  }

  public static void addToCart() {
    addToCartButton.shouldBe(Condition.visible, Condition.enabled).click();
  }

  public static void addedToCartSuccessfully() {
    addToCartConfirmationMessage
        .shouldBe(Condition.visible, Condition.exactText("Produkt został dodany do koszyka."));
  }

}
