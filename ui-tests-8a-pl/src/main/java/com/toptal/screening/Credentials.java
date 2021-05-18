package com.toptal.screening;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Credentials {
  public static final Credentials DEFAULT_USER =
      Credentials.builder().email("IuA8vp5hf2@IuA8vp5hf2.com").password("zFjN65ukEQ!1I")
                 .firstname("jHkmKZywnu").lastname("aodSQCzALN").build();

  private String email;
  private String password;
  private String firstname;
  private String lastname;

  public String getContactData() {
    return String.format("%s %s\n%s", firstname, lastname, email);
  }

  public String getFullName() {
    return String.format("%s %s", firstname, lastname);
  }

}
