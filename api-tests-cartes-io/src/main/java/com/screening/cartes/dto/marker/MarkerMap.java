package com.screening.cartes.dto.marker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class MarkerMap {

  @JsonProperty("updated_at")
  private String updatedAt;

  @JsonProperty("options")
  private MarketMapOptions options;

  @JsonProperty("description")
  private String description;

  @JsonProperty("privacy")
  private String privacy;

  @JsonProperty("users_can_create_markers")
  private String usersCanCreateMarkers;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("title")
  private String title;

  @JsonProperty("uuid")
  private String uuid;

  @JsonProperty("slug")
  private String slug;
}