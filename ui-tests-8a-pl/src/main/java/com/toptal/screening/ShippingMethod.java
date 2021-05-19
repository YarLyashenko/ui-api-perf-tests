package com.toptal.screening;

public enum ShippingMethod {
  POLISH_POST_PREPAID("smenpbn_smenpbn"),
  INPOST_STANDARD("inpost_standard"),
  POLISH_POST_PAID_ON_DELIVERY("smenpbnp_smenpbnp"),
  DHL_KURIER("dhl24pl_courier"),
  GLIWICE_STORE_PICKUP("storepickup_storepickup"),
  WARSAW_STORE_PICKUP("storepickup_storepickup2");


  public final String value;

  private ShippingMethod(String value) {
    this.value = value;
  }
}
