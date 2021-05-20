package com.toptal.screening;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.toptal.screening.pages.AccountPage;
import com.toptal.screening.pages.HeaderPanel;
import com.toptal.screening.pages.LoginPopup;
import com.toptal.screening.pages.RegisterPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegisterTest extends BaseTest {

  @Test
  @DisplayName("Register from main page")
  @Description("Register new customer from main page via pop-up")
  public void registerFromMainPage() {
    CustomerData credentials = CustomerData.generateRandomUser();

    HeaderPanel.clickLogin();
    LoginPopup.clickRegisterButton();

    assertEquals(Url.REGISTER_URL, url(),
        "Registration page should be opened.");

    RegisterPage.registerUser(credentials);

    Assertions.assertAll("Verify registration results:",
        () -> assertEquals(credentials.getContactData(), AccountPage.getContactData(),
            "Registration failed: user data incorrect."),
        () -> assertEquals(Url.ACCOUNT_URL, url(),
            "Registration should finish at user's account page."));

  }
}
