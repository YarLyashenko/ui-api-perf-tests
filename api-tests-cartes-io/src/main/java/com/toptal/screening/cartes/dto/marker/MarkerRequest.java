package com.toptal.screening.cartes.dto.marker;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarkerRequest {

  @JsonProperty("category_name")
  private String categoryName;

  @JsonProperty("lng")
  private Double longitude;

  @JsonProperty("map_token")
  private String mapToken;

  @JsonProperty("description")
  private String description;

  @JsonProperty("category")
  private Integer category;

  @JsonProperty("loading")
  private Boolean loading;

  @JsonProperty("lat")
  private Double latitude;

  public static MarkerRequest generateRandomRequest() {
    return generateRandomRequest(RandomStringUtils.randomAlphabetic(32));
  }

  public static MarkerRequest generateRandomRequest(String mapToken) {
    return
        MarkerRequest.builder()
                     .mapToken(mapToken)
                     .latitude(RandomUtils.nextDouble(0, 90))
                     .longitude(RandomUtils.nextDouble(0, 180))
                     .description(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(10, 30)))
                     .category(-1)
                     .categoryName(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(10, 30)))
                     .build();
  }
}