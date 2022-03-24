package com.screening;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Url {
  public static final String BASE_URL = "https://8a.pl/";
  public static final String ACCOUNT_URL = BASE_URL + "customer/account/";
  public static final String REGISTER_URL = ACCOUNT_URL + "create/";
  public static final String LOGIN_URL = ACCOUNT_URL + "login/";
  public static final String CHECKOUT_URL = BASE_URL + "checkout/";
}
