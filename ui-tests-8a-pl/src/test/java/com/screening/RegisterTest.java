package com.screening;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.screening.pages.AccountPage;
import com.screening.pages.HeaderPanel;
import com.screening.pages.LoginPopup;
import com.screening.pages.RegisterPage;
import io.qameta.allure.Description;
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

    AccountPage.verifyContactData(credentials.getContactData());
    assertEquals(Url.ACCOUNT_URL, url(), "Registration should finish at user's account page.");

  }
}
