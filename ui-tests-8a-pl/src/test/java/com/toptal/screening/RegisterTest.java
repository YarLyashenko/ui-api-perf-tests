package com.toptal.screening;

import com.toptal.screening.pages.AccountPage;
import com.toptal.screening.pages.LoginPopup;
import com.toptal.screening.pages.PageHeader;
import com.toptal.screening.pages.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterTest extends BaseTest {
  private Credentials credentials;

  @BeforeEach
  public void createUser() {
    credentials = Credentials.builder()
                             .firstname(RandomStringUtils.randomAlphabetic(SYMBOLS_COUNT))
                             .lastname(RandomStringUtils.randomAlphabetic(SYMBOLS_COUNT))
                             .email(generateEmail())
                             .password(generatePassword())
                             .build();
  }

  @Test
  public void registerFromMainPage() {
    PageHeader.clickLogin();
    LoginPopup.clickRegisterButton();
    RegisterPage.registerUser(credentials);

    Assertions.assertEquals(credentials.getContactData(), AccountPage.getContactData(),
        "Registration failed: user data incorrect");
  }
}
