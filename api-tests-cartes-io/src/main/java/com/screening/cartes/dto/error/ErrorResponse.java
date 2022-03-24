package com.screening.cartes.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(fluent = true)
public class ErrorResponse {

  @JsonProperty("message")
  private String message;

  @JsonProperty("errors")
  private Errors errors;
}
