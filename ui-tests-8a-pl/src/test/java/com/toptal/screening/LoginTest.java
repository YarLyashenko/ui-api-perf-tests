package com.toptal.screening;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.toptal.screening.pages.LoginPopup;
import com.toptal.screening.pages.PageHeader;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

  @Test
  public void loginFromMainPage() {
    PageHeader.clickLogin();

    LoginPopup.shouldBeVisible();

    LoginPopup.performLogin(Credentials.DEFAULT_USER);
    LoginPopup.reCaptchaWorkaroundOnLogin();

    LoginPopup.shouldBeNotVisible();

    assertEquals(Credentials.DEFAULT_USER.getFullName(), PageHeader.getUserName(),
        "Displayed user's full name is incorrect.");
  }
}
