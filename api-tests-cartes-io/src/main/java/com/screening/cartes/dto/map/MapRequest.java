package com.screening.cartes.dto.map;

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
public class MapRequest {
  @JsonProperty("title")
  private String title;
  @JsonProperty("slug")
  private String slug;
  @JsonProperty("description")
  private String description;
  @JsonProperty("privacy")
  private String privacy;
  @JsonProperty("users_can_create_markers")
  private String usersCanCreateMarkers;

  public static MapRequest generateRandomRequest() {
    return
        MapRequest.builder()
                  .title(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(10, 30)))
                  .description(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(50, 80)))
                  .privacy("public")
                  .usersCanCreateMarkers("yes")
                  .build();
  }
}
