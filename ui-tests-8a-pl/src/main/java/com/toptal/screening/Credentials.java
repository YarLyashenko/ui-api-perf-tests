package com.toptal.screening;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Credentials {
  public static final Credentials DEFAULT_USER =
      Credentials.builder().email("f0Icfr1Czl@f0Icfr1Czl.com").password("DBleoMbTzc!1I")
                 .firstname("MGrAlTMHiT").lastname("xHXdoRCVfe").build();

  private String email;
  private String password;
  private String firstname;
  private String lastname;

  public String getContactData() {
    return String.format("%s %s\n%s", firstname, lastname, email);
  }

}
