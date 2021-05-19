package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import java.util.List;

public class ItemsPage {

  private static ElementsCollection activeFilters = $$("li.am-shopby-item");

  public static List<String> getActiveFilters() {
    return activeFilters.texts();
  }
}
