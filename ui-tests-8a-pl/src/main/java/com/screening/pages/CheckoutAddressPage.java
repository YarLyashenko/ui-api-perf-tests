package com.screening.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.screening.Constants;
import com.screening.CustomerData;
import com.screening.ShippingMethod;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutAddressPage {
  private static SelenideElement emailInput = $("input#customer-email");
  private static SelenideElement addressForm = $("#shipping-new-address-form");
  private static SelenideElement firstname = addressForm.$("input[name='firstname']");
  private static SelenideElement lastname = addressForm.$("input[name='lastname']");
  private static SelenideElement telephone = addressForm.$("input[name='telephone']");
  private static SelenideElement recipient = addressForm.$("input[name='company']");
  private static SelenideElement street = addressForm.$("input[name='street[0]']");
  private static SelenideElement houseNumber = addressForm.$("input[name='street[1]']");
  private static SelenideElement apartmentNumber = addressForm.$("input[name='street[2]']");
  private static SelenideElement country = addressForm.$("select[name='country_id']");
  private static SelenideElement city = addressForm.$("input[name='city']");
  private static SelenideElement postcode = addressForm.$("input[name='postcode']");
  private static SelenideElement shippingMethods = $("#checkout-shipping-method-load");
  private static SelenideElement submitAddressButton =
      $("#shipping-method-buttons-container button[type='submit']");

  @Step("Select shipping method {shippingMethod}")
  public static void selectShippingMethod(ShippingMethod shippingMethod) {
    submitAddressButton.scrollTo();

    shippingMethods
        .find(By.xpath(String.format(".//tr[.//input[@value='%s']]", shippingMethod.value)))
        .scrollIntoView(Constants.SCROLL_OPTIONS)
        .shouldBe(Condition.visible, Condition.enabled)
        .click();
  }

  @Step("Click submit button on checkout page with address")
  public static void clickSubmitAddressButton() {
    submitAddressButton.scrollIntoView(Constants.SCROLL_OPTIONS)

                       .shouldBe(Condition.visible, Condition.enabled)
                       .click();
  }

  @Step("Fill customer and shipping details for customer: {customerData}")
  public static void fillCustomerData(CustomerData customerData) {
    emailInput.shouldBe(Condition.visible).setValue(customerData.getEmail());
    firstname.shouldBe(Condition.visible).setValue(customerData.getFirstname());
    lastname.shouldBe(Condition.visible).setValue(customerData.getLastname());
    telephone.shouldBe(Condition.visible).setValue(customerData.getTelephone());
    recipient.shouldBe(Condition.visible).setValue(customerData.getRecipient());
    street.shouldBe(Condition.visible).setValue(customerData.getStreet());
    houseNumber.shouldBe(Condition.visible).setValue(customerData.getHouseNumber());
    apartmentNumber.shouldBe(Condition.visible).setValue(customerData.getApartmentNumber());
    country.shouldBe(Condition.visible).selectOptionContainingText(customerData.getCountry());
    city.shouldBe(Condition.visible).setValue(customerData.getCity());
    postcode.shouldBe(Condition.visible).setValue(customerData.getPostcode());
  }

}
