package com.screening.cartes.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class Errors {

  @JsonProperty("category_name")
  private List<String> categoryName;

  @JsonProperty("lng")
  private List<String> longitude;

  @JsonProperty("category")
  private List<String> category;

  @JsonProperty("lat")
  private List<String> latitude;
}