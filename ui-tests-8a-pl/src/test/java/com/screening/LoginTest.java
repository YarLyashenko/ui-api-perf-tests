package com.screening;

import com.screening.pages.HeaderPanel;
import com.screening.pages.LoginPopup;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

  @Test
  @DisplayName("Login from main page")
  @Description("Perform login from main page via pop-up")
  public void loginFromMainPage() {
    HeaderPanel.clickLogin();

    LoginPopup.shouldBeVisible();

    LoginPopup.performLogin(CustomerData.DEFAULT_USER);
    LoginPopup.reCaptchaWorkaroundOnLogin();

    LoginPopup.shouldBeNotVisible();

    HeaderPanel.verifyCustomerName(CustomerData.DEFAULT_USER.getFullName());
  }
}
