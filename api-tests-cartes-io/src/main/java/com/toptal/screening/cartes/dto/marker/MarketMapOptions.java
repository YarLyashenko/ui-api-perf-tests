package com.toptal.screening.cartes.dto.marker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class MarketMapOptions {

  @JsonProperty("default_expiration_time")
  private String defaultExpirationTime;

  @JsonProperty("limit_to_geographical_body_type")
  private String limitToGeographicalBodyType;
}