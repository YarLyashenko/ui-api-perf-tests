package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;

public class ItemsPage {

  private static ElementsCollection activeFilters = $$("li.am-shopby-item");
  private static ElementsCollection itemTiles = $$(".product-item-info");

  public static List<String> getActiveFilters() {
    return activeFilters.texts();
  }

  public static void clickRandomItem() {
    itemTiles.get(RandomUtils.nextInt(0, itemTiles.size()))
             .scrollTo()
             .click();
  }
}
