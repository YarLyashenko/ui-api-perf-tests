package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.toptal.screening.Constants;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.opentest4j.AssertionFailedError;

public class CartPopup {
  private static final String CART_ITEMS_CSS = "li.item.product.product-item";
  private static final String ITEM_UPDATE_QUANTITY_CSS = ".product.actions .primary a";
  private static final String ITEM_REMOVE_CSS = ".product.actions .secondary a";

  private static SelenideElement miniCartPanel = $(".block-minicart");
  private static SelenideElement itemsInCartCount = miniCartPanel.$("span.count");
  private static SelenideElement checkoutButton = miniCartPanel.$("button#top-cart-btn-checkout");
  private static SelenideElement openBigCartLink = miniCartPanel.$("a.action.viewcart");
  private static ElementsCollection cartItems = miniCartPanel.$$(CART_ITEMS_CSS);
  private static ElementsCollection cartItemsNames =
      miniCartPanel.$$(CART_ITEMS_CSS + " .product-item-name");

  @Step("Verify amount of items in cart")
  public static void verifyItemsCountInCart(int expectedCount) {
    itemsInCartCount.shouldBe(
        Condition.visible,
        Condition.exactText(String.valueOf(expectedCount)));

  }

  @Step("Wait cart to update")
  public static void waitCartToUpdate(int previousCartItemsCount) {
    itemsInCartCount.shouldNotHave(Condition.exactText(String.valueOf(previousCartItemsCount)));
  }

  @Step("Click checkout button")
  public static void clickCheckout() {
    checkoutButton.shouldBe(Condition.visible, Condition.enabled)
                  .click();
  }

  //should scroll to element to get text loaded
  @Step("Scroll thru all items in cart")
  public static List<String> getNamesOfItemsInCart() {
    return cartItemsNames.stream()
                         .map(e -> e.scrollIntoView(Constants.SCROLL_OPTIONS)
                                    .shouldBe(Condition.visible)
                                    .text())
                         .collect(Collectors.toList());
  }

  @Step("Click remove from cart on item {itemName}")
  public static void clickRemoveItemFromCart(String itemName) {
    getItemInCart(itemName).$(ITEM_REMOVE_CSS)
                           .scrollIntoView(Constants.SCROLL_OPTIONS)
                           .shouldBe(Condition.visible, Condition.enabled)
                           .click();
  }

  private static SelenideElement getItemInCart(String itemName) {
    return cartItems.stream()
                    .filter(e -> e.scrollIntoView(Constants.SCROLL_OPTIONS)
                                  .shouldBe(Condition.visible)
                                  .has(Condition.text(itemName)))
                    .findFirst()
                    .orElseThrow(
                        () -> new AssertionFailedError("No item in cart with name: " + itemName));
  }

}
