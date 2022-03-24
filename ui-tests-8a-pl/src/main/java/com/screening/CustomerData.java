package com.screening;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerData {
  public static final CustomerData DEFAULT_USER =
      CustomerData.builder()
                  .email("vqg5gFqAW@vqg5gFqAW.com").password("Kc13gYoIY!1I")
                  .firstname("vgBlHqbMZMq").lastname("vqjvrAdga").build();


  private String email;
  private String password;
  private String firstname;
  private String lastname;
  private String telephone;
  private String recipient;
  private String street;
  private String houseNumber;
  private String apartmentNumber;
  private String country;
  private String city;
  private String postcode;
  private ShippingMethod shippingMethod;

  public static CustomerData generateRandomUser() {
    return CustomerData.builder()
                       .email(String.format("%1$s@%1$s.com", randomAlphanumeric(nextInt(6, 12))))
                       .password("!1I" + randomAlphanumeric(nextInt(6, 12)))
                       .firstname("name" + randomAlphabetic(nextInt(2, 15)))
                       .lastname("lastname" + randomAlphabetic(nextInt(2, 15)))
                       .telephone(randomNumeric(9))
                       .recipient("recipient" + randomAlphabetic(nextInt(2, 15)))
                       .street("street" + randomAlphabetic(nextInt(2, 15)))
                       .houseNumber(randomNumeric(3))
                       .apartmentNumber(randomNumeric(2))
                       .country("Polska")
                       .city("city" + randomAlphabetic(nextInt(2, 15)))
                       .postcode(String.format("%s-%s", randomNumeric(2), randomNumeric(3)))
                       .shippingMethod(ShippingMethod.GLIWICE_STORE_PICKUP)
                       .build();
  }

  public String getContactData() {
    return String.format("%s %s\n%s", firstname, lastname, email);
  }

  public String getFullName() {
    return String.format("%s %s", firstname, lastname);
  }

  public String getShippingInfo() {
    return String.format("%s %s\n%s, %s, %s\n%s, %s\n%s\n%s",
        firstname, lastname, street, houseNumber,
        apartmentNumber, city, postcode, country, telephone);
  }

}
