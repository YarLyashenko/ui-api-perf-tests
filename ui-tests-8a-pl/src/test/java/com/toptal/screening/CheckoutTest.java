package com.toptal.screening;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.toptal.screening.pages.CartPopup;
import com.toptal.screening.pages.CheckoutAddressPage;
import com.toptal.screening.pages.CheckoutPaymentPage;
import com.toptal.screening.pages.HeaderPanel;
import com.toptal.screening.pages.ItemsPage;
import com.toptal.screening.pages.ProductPage;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest {

  @Test
  public void addSeveralItemsToCart() {

    //search and add item to cart
    HeaderPanel.searchBy(DEFAULT_SEARCH_VALUE_SHOVEL);
    ItemsPage.clickRandomItem();
    ProductPage.addToCart();
    ProductPage.addedToCartSuccessfully();

    //open cart and proceed with checkout
    HeaderPanel.openCartPopup();
    CartPopup.clickCheckout();

    //fill shipment and payment info
    CustomerData customer = CustomerData.generateRandomUser();
    CheckoutAddressPage.fillCustomerData(customer);
    CheckoutAddressPage.selectShippingMethod(customer.getShippingMethod());
    CheckoutAddressPage.clickSubmitAddressButton();

    //verify shipping address
    assertEquals(customer.getShippingInfo(), CheckoutPaymentPage.getShippingInfo(),
        "Shipping address is not the same as submitted on checkout page.");

    //Submit order is disabled to not spam a real store with test orders
    //    CheckoutPaymentPage.clickSubmitOrderButton();
    //todo: add verification for successfully submitted order
  }
}
