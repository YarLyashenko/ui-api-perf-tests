package com.screening.cartes;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.http.ContentType.JSON;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.XmlConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConnectionUtils {
    public static final XmlConfig XML_CONFIG =
        XmlConfig.xmlConfig().allowDocTypeDeclaration(true).disableLoadingOfExternalDtd();
    private static final RestAssuredConfig REST_ASSURED_CONFIG =
        RestAssuredConfig.config().objectMapperConfig(
            objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false)
                .registerModule(new ParameterNamesModule())
            ));


    public static RequestSpecification getRequestSpec(final String uri) {
        return new RequestSpecBuilder()
            .setConfig(REST_ASSURED_CONFIG)
            .setContentType(JSON)
            .setAccept(JSON)
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new AllureRestAssured())
            .setBaseUri(uri)
            .build();
    }

    public static Response sendRequest(final RequestSpecification requestSpecification,
                                       final HttpMethod httpMethod,
                                       final String uri) {
        switch (httpMethod) {
            case GET:
                return given(requestSpecification).get(uri);
            case POST:
                return given(requestSpecification).post(uri);
            case PUT:
                return given(requestSpecification).put(uri);
            case HEAD:
                return given(requestSpecification).head(uri);
            case OPTIONS:
                return given(requestSpecification).options(uri);
            case DELETE:
                return given(requestSpecification).delete(uri);
            case PATCH:
                return given(requestSpecification).patch(uri);
            default:
                throw new IllegalArgumentException(String.valueOf(httpMethod));
        }
    }
}
