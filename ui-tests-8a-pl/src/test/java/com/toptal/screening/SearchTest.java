package com.toptal.screening;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.toptal.screening.pages.FilterPanel;
import com.toptal.screening.pages.HeaderPanel;
import com.toptal.screening.pages.ItemsPage;
import io.qameta.allure.Description;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseTest {

  @Test
  @DisplayName("Search by three parameters")
  @Description("Perform search and filter results by three different criteria")
  public void searchByThreeParameters() {

    Map<String, String> searchCriteria = Map.of(
        "Typ produktu", "Okulary przeciwsłoneczne",
        "Producent", "Goggle",
        "Płeć", "Unisex");

    HeaderPanel.searchBy(DEFAULT_SEARCH_VALUE_GLASSES);

    FilterPanel.shouldBeDisplayed();
    searchCriteria.forEach(FilterPanel::filterByValues);

    List<String> actualFilters = ItemsPage.getActiveFilters();
    actualFilters.sort(Comparator.naturalOrder());

    List<String> expectedFilters = searchCriteria.entrySet()
                                                 .stream().map(e -> e.getKey() + " " + e.getValue())
                                                 .sorted()
                                                 .collect(Collectors.toList());

    assertEquals(expectedFilters, actualFilters,
        "Displayed enabled filters are not the same as expected.");
  }
}
