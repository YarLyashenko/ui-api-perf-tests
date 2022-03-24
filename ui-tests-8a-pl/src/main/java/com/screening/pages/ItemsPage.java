package com.screening.pages;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;

public class ItemsPage {

  private static ElementsCollection activeFilters = $$("li.am-shopby-item");
  private static ElementsCollection itemTiles = $$(".product-item-info");

  public static List<String> getActiveFilters() {
    return activeFilters.texts();
  }

  @Step("Open random product from current page")
  public static void clickRandomItem() {
    itemTiles.get(RandomUtils.nextInt(0, itemTiles.size()))
             .scrollTo()
             .click();
  }
}
