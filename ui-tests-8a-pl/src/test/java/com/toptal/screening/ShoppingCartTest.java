package com.toptal.screening;

import static com.codeborne.selenide.Selenide.back;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.toptal.screening.pages.CartPopup;
import com.toptal.screening.pages.HeaderPanel;
import com.toptal.screening.pages.ItemsPage;
import com.toptal.screening.pages.ProductPage;
import com.toptal.screening.pages.RemoveFromCartConfirmationPopup;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest extends BaseTest {

  @Test
  public void addSeveralItemsToCart() {
    //add items to cart
    List<String> expectedCartItems =
        addItemsToCart(DEFAULT_SEARCH_VALUE_GLASSES, RandomUtils.nextInt(2, 6));

    //open cart
    HeaderPanel.openCartPopup();

    //verify total items count
    assertEquals(String.valueOf(expectedCartItems.size()), CartPopup.getAmountOfItemsInCart(),
        "Count of items in cart is not the same as expected.");

    //verify items in cart have same title
    List<String> actualCartItems = CartPopup.getNamesOfItemsInCart();
    actualCartItems.sort(Comparator.naturalOrder());
    expectedCartItems.sort(Comparator.naturalOrder());

    assertEquals(expectedCartItems, actualCartItems,
        "Items in cart are not the same as expected.");
  }

  @Test
  public void removeItemsFromCart() {
    final int amountAddedToCart = RandomUtils.nextInt(3, 5);
    final int amountLeftInCart = 2;

    //add items to cart
    List<String> cartItems =
        addItemsToCart(DEFAULT_SEARCH_VALUE_SHOVEL, amountAddedToCart);

    //open cart
    HeaderPanel.openCartPopup();

    //remove few items
    for (int i = 0; i < cartItems.size() - amountLeftInCart; i++) {
      CartPopup.clickRemoveItemFromCart(cartItems.get(i));
      RemoveFromCartConfirmationPopup.accept();
    }

    //verify total amount left in cart
    assertEquals(String.valueOf(amountLeftInCart), CartPopup.getAmountOfItemsInCart(),
        "Count of items in cart is not the same as expected.");

    //verify items in cart have same title
    List<String> expectedCartItems =
        cartItems.subList(amountAddedToCart - amountLeftInCart, amountAddedToCart);
    expectedCartItems.sort(Comparator.naturalOrder());

    List<String> actualCartItems = CartPopup.getNamesOfItemsInCart();
    actualCartItems.sort(Comparator.naturalOrder());

    assertEquals(expectedCartItems, actualCartItems,
        "Items in cart are not the same as expected.");
  }

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
