package com.toptal.screening.cartes;

import static io.restassured.RestAssured.given;

import com.toptal.screening.cartes.dto.error.ErrorResponse;
import com.toptal.screening.cartes.dto.map.MapRequest;
import com.toptal.screening.cartes.dto.map.MapResponse;
import com.toptal.screening.cartes.dto.marker.MarkerRequest;
import com.toptal.screening.cartes.dto.marker.MarkerResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class CartesService {
  public static final String CARTES_URI = "https://cartes.io/api/";
  public static final String MAPS_PATH = "maps/";
  public static final String MAP_ID = "map-id";
  public static final String MARKER_ID = "marker-id";
  public static final String MARKERS_PATH = String.format("%s{%s}/markers/", MAPS_PATH, MAP_ID);
  public static final String MARKER_ID_PATH = String.format("%s{%s}", MARKERS_PATH, MARKER_ID);

  public static final RequestSpecification SPECIFICATION = ConnectionUtils.getRequestSpec(
      CARTES_URI);
  private static final int RETRY_AMOUNT = 10;
  private static final int MILLIS_500 = 500;


  public static MapResponse createMap() {
    return createMap(MapRequest.generateRandomRequest());
  }

  @Step("Create new map")
  public static MapResponse createMap(MapRequest request) {
    return given(CartesService.SPECIFICATION)
        .body(request)
        .post(CartesService.MAPS_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .as(MapResponse.class);
  }

  @Step("Send {httpMethod} request to the Markers endpoint")
  public static ErrorResponse sendNotAllowedMarkersRequest(final HttpMethod httpMethod,
                                                           String mapId) {

    final String uri = CartesService.MARKERS_PATH.replace("{" + MAP_ID + "}", mapId);

    return ConnectionUtils
        .sendRequest(CartesService.SPECIFICATION, httpMethod, uri)
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
        .extract()
        .as(ErrorResponse.class);
  }

  @Step("Create new marker")
  public static MarkerResponse createMarker(String mapId, MarkerRequest body) {
    return postMarker(mapId, body, HttpStatus.SC_CREATED)
        .extract()
        .as(MarkerResponse.class);
  }

  @Step("Create new marker")
  public static ErrorResponse createMarker(String mapId, MarkerRequest body,
                                           int expectedStatusCode) {
    return postMarker(mapId, body, expectedStatusCode)
        .extract()
        .as(ErrorResponse.class);
  }

  @Step("Send POST request to the markers endpoint")
  public static ValidatableResponse postMarker(String mapId, MarkerRequest body,
                                               int expectedStatusCode) {
    return given(CartesService.SPECIFICATION)
        .pathParam(MAP_ID, mapId)
        .body(body)
        .post(CartesService.MARKERS_PATH)
        .then()
        .assertThat()
        .statusCode(expectedStatusCode);
  }

  @Step("Update marker")
  public static MarkerResponse updateMarker(String mapId, Integer markerId, MarkerRequest body) {
    return putMarker(mapId, markerId, body, HttpStatus.SC_OK)
        .extract()
        .as(MarkerResponse.class);
  }

  @Step("Update marker")
  public static ErrorResponse updateMarker(String mapId, Integer markerId, MarkerRequest body,
                                           int expectedStatusCode) {
    return putMarker(mapId, markerId, body, expectedStatusCode)
        .extract()
        .as(ErrorResponse.class);
  }

  @Step("Send PUT request to the marker id endpoint")
  public static ValidatableResponse putMarker(String mapId, Integer markerId, MarkerRequest body,
                                              int expectedStatusCode) {
    return given(CartesService.SPECIFICATION)
        .pathParam(MAP_ID, mapId)
        .pathParam(MARKER_ID, markerId)
        .body(body)
        .put(CartesService.MARKER_ID_PATH)
        .then()
        .assertThat()
        .statusCode(expectedStatusCode);
  }
}
