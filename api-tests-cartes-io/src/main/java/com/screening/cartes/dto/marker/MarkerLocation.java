package com.screening.cartes.dto.marker;

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
public class MarkerLocation {


  @JsonProperty("coordinates")
  private List<Double> coordinates;

  @JsonProperty("type")
  private String type;
}