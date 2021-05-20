package com.toptal.screening;

import static com.codeborne.selenide.Selenide.back;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.toptal.screening.pages.CartPopup;
import com.toptal.screening.pages.HeaderPanel;
import com.toptal.screening.pages.ItemsPage;
import com.toptal.screening.pages.ProductPage;
import com.toptal.screening.pages.RemoveFromCartConfirmationPopup;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest extends BaseTest {

  @Test
  @DisplayName("Add multiple items to cart")
  @Description("Perform multiple searches and add few items to cart")
  public void addSeveralItemsToCart() {
    //add items to cart
    List<String> expectedCartItems =
        addItemsToCart(DEFAULT_SEARCH_VALUE_GLASSES, RandomUtils.nextInt(2, 6));

    //open cart
    HeaderPanel.openCartPopup();

    //verify total items count
    CartPopup.verifyItemsCountInCart(expectedCartItems.size());

    //verify items in cart have same title
    List<String> actualCartItems = CartPopup.getNamesOfItemsInCart();
    actualCartItems.sort(Comparator.naturalOrder());
    expectedCartItems.sort(Comparator.naturalOrder());

    assertEquals(expectedCartItems, actualCartItems,
        "Items in cart are not the same as expected.");
  }

  @Test
  @DisplayName("Remove multiple items to cart")
  @Description("Add few items to cart and remove some of them, but not all")
  public void removeItemsFromCart() {
    final int amountAddedToCart = RandomUtils.nextInt(3, 5);
    final int amountLeftInCart = 2;

    //add items to cart
    List<String> cartItems = addItemsToCart(DEFAULT_SEARCH_VALUE_GLASSES, amountAddedToCart);

    //open cart
    HeaderPanel.openCartPopup();

    //remove few items
    for (int i = cartItems.size() - 1; i >= amountLeftInCart; i--) {
      CartPopup.clickRemoveItemFromCart(cartItems.get(i));
      RemoveFromCartConfirmationPopup.accept();
      RemoveFromCartConfirmationPopup.shouldBeClosed();
      CartPopup.waitCartToUpdate(i + 1);
    }

    //verify total amount left in cart
    CartPopup.verifyItemsCountInCart(amountLeftInCart);

    //verify items in cart have same title
    List<String> expectedCartItems = cartItems.subList(0, amountLeftInCart);
    expectedCartItems.sort(Comparator.naturalOrder());

    List<String> actualCartItems = CartPopup.getNamesOfItemsInCart();
    actualCartItems.sort(Comparator.naturalOrder());

    assertEquals(expectedCartItems, actualCartItems,
        "Items in cart are not the same as expected.");
  }

  @Step("Add {itemsCount} item(s) to cart by search request '{searchRequest}'")
  private List<String> addItemsToCart(String searchRequest, int itemsCount) {
    HeaderPanel.searchBy(searchRequest);
    List<String> addedItems = new ArrayList<>();

    for (int i = 0; i < itemsCount; i++) {
      ItemsPage.clickRandomItem();
      ProductPage.addToCart();
      ProductPage.addedToCartSuccessfully();
      addedItems.add(ProductPage.getName());
      back();
    }

    return addedItems;
  }
}
