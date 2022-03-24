package com.toptal.screening.cartes;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.toptal.screening.cartes.dto.map.MapResponse;
import com.toptal.screening.cartes.dto.marker.MarkerRequest;
import com.toptal.screening.cartes.dto.marker.MarkerResponse;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import java.util.UUID;
import lombok.val;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MarkersTest {

  private static MapResponse mapResponse;
  private static MarkerResponse createdMarker;

  @BeforeAll
  public static void createMap() {
    mapResponse = CartesService.createMap();
    val markerRequest = MarkerRequest.generateRandomRequest(mapResponse.token());
    createdMarker = CartesService.createMarker(mapResponse.uuid(), markerRequest);
  }

  @DisplayName("Verify unsupported http methods for markers endpoint")
  @ParameterizedTest(name = "{0}:{displayName}")
  @EnumSource(value = HttpMethod.class, names = {"DELETE", "PUT", "PATCH"})
  public void unsupportedMarkersMethods(HttpMethod method) {
    val errorResponse =
        CartesService.sendNotAllowedMarkersRequest(method, mapResponse.uuid());

    Assertions.assertThat(errorResponse.message()).isEqualTo("The " + method +
        " method is not supported for this route. Supported methods: GET, HEAD, POST.");
  }

  //POST tests

  @DisplayName("POST: create marker on a public map")
  @Test
  public void createMarker() {
    val markerRequest = MarkerRequest.generateRandomRequest();
    val markerResponse = CartesService.createMarker(mapResponse.uuid(),
        markerRequest);

    verifyMarkerResponse(markerRequest, markerResponse);
  }

  @DisplayName("POST: create a marker without required fields")
  @Test
  public void postMarkerWithoutRequiredFields() {
    val markerRequest = new MarkerRequest();

    val markerResponse =
        CartesService.createMarker(mapResponse.uuid(), markerRequest,
            HttpStatus.SC_UNPROCESSABLE_ENTITY);

    assertAll("Verification of marker response",
        () -> Assertions.assertThat(markerResponse.message()).isEqualTo("The given data was invalid."),
        () -> Assertions.assertThat(markerResponse.errors().category().get(0))
            .isEqualTo("The category field is required when category name is not present."),
        () -> Assertions.assertThat(markerResponse.errors().latitude().get(0))
            .isEqualTo("The lat field is required."),
        () -> Assertions.assertThat(markerResponse.errors().longitude().get(0))
            .isEqualTo("The lng field is required."),
        () -> Assertions.assertThat(markerResponse.errors().categoryName().get(0))
            .isEqualTo("The category name field is required when category is not present."));

  }

  @DisplayName("POST: create a marker for non-existent map")
  @Test
  public void postMarkerForNonExistentMap() {
    val markerRequest = MarkerRequest.generateRandomRequest();
    val mapId = UUID.randomUUID().toString();

    val markerResponse =
        CartesService.createMarker(mapId, markerRequest, HttpStatus.SC_NOT_FOUND);

    Assertions.assertThat(markerResponse.message())
        .isEqualTo("No query results for model [App\\Models\\Map] " + mapId);
  }

  @Step("Verify response for marker request")
  private void verifyMarkerResponse(MarkerRequest request, MarkerResponse response) {
    assertAll("Verification of marker response",
        () -> Assertions.assertThat(response.updatedAt()).isNotEmpty(),
        () -> Assertions.assertThat(response.createdAt()).isNotEmpty(),
        () -> Assertions.assertThat(response.description()).isEqualTo(request.description()),
        () -> Assertions.assertThat(response.category()).isNotNull(),
        () -> Assertions.assertThat(response.category().name()).isEqualTo(request.categoryName()),
        () -> Assertions.assertThat(response.category().id()).isGreaterThan(0),
        () -> Assertions.assertThat(response.category().icon()).isNotEmpty(),
        () -> Assertions.assertThat(response.location()).isNotNull(),
        () -> Assertions.assertThat(response.location().coordinates()).isNotEmpty(),
        () -> Assertions.assertThat(response.location().coordinates().get(0)).isEqualTo(request.latitude()),
        () -> Assertions.assertThat(response.location().coordinates().get(1)).isEqualTo(request.longitude()));
  }


  //PUT tests

  @Issue("PUT call for marker update fails with 403 Forbidden")
  @DisplayName("PUT: update marker on a map")
  @Test
  public void updateMarker() {
    val updateRequest = MarkerRequest.generateRandomRequest(mapResponse.token());

    val updatedMarker = CartesService
        .updateMarker(mapResponse.uuid(), createdMarker.id(), updateRequest);

    verifyMarkerResponse(updateRequest, updatedMarker);
  }

  @Issue("PUT call for marker update fails with 403 Forbidden")
  @DisplayName("PUT: update marker on a map without required fields")
  @Test
  public void updateMarkerWithoutRequiredFields() {
    val updateRequest = new MarkerRequest();

    val markerResponse = CartesService.updateMarker(mapResponse.uuid(),
        createdMarker.id(), updateRequest, HttpStatus.SC_UNPROCESSABLE_ENTITY);

    assertAll("Verification of marker response",
        () -> Assertions.assertThat(markerResponse.message()).isEqualTo("The given data was invalid."),
        () -> Assertions.assertThat(markerResponse.errors().category().get(0))
            .isEqualTo("The category field is required when category name is not present."),
        () -> Assertions.assertThat(markerResponse.errors().latitude().get(0))
            .isEqualTo("The lat field is required."),
        () -> Assertions.assertThat(markerResponse.errors().longitude().get(0))
            .isEqualTo("The lng field is required."),
        () -> Assertions.assertThat(markerResponse.errors().categoryName().get(0))
            .isEqualTo("The category name field is required when category is not present."));
  }

  @DisplayName("PUT: update non-existent marker on a map")
  @Test
  public void updateMarkerForNonExistentId() {
    val updateRequest = MarkerRequest.generateRandomRequest(mapResponse.token());

    val markerId = RandomUtils.nextInt();
    val markerResponse = CartesService
        .updateMarker(mapResponse.uuid(), markerId, updateRequest, HttpStatus.SC_NOT_FOUND);

    Assertions.assertThat(markerResponse.message())
        .isEqualTo("No query results for model [App\\Models\\Marker] " + markerId);
  }


}