package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class ProductPage {

  private static SelenideElement name = $(".page-title span");
  private static SelenideElement addToCartButton = $("button#product-addtocart-button");
  private static SelenideElement addToCartConfirmationMessage = $(".product-add-form .message");
  private static SelenideElement size = $(".swatch-option");

  public static String getName() {
    return name.shouldBe(Condition.visible).text();
  }

  @Step("Add item to cart")
  public static void addToCart() {
    addToCartButton.shouldBe(Condition.visible);

    if (size.isDisplayed()) {
      size.click();
    }
    addToCartButton.shouldBe(Condition.enabled).click();
  }

  @Step("Verify confirmation for adding item to cart")
  public static void addedToCartSuccessfully() {
    addToCartConfirmationMessage
        .shouldBe(Condition.visible, Condition.exactText("Produkt został dodany do koszyka."));
  }

}
