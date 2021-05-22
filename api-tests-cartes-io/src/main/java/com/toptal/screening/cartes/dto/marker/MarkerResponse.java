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
public class MarkerResponse {

  @JsonProperty("expires_at")
  private String expiresAt;

  @JsonProperty("category_id")
  private Integer categoryId;

  @JsonProperty("updated_at")
  private String updatedAt;

  @JsonProperty("description")
  private String description;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("location")
  private MarkerLocation location;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("category")
  private MarkerCategory category;

  @JsonProperty("map")
  private MarkerMap map;

  @JsonProperty("token")
  private String token;
}