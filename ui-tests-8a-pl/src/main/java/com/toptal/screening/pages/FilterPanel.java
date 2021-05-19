package com.toptal.screening.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class FilterPanel {

  private static SelenideElement panel = $(".filters-drawer");
  private static ElementsCollection filterOptions = $$(".filter-options-title");
  private static SelenideElement expandedFilterTitle =
      $(".filter-options-title[aria-expanded=true]");
  private static ElementsCollection expandedFilterOptionList =
      $$(".filter-options-content[aria-hidden=false] li.item");

  public static void clickFilterTab(String tabName) {
    filterOptions.find(Condition.exactText(tabName))
                 .shouldBe(Condition.visible, Condition.enabled)
                 .click();
  }

  public static void filterByValues(String category, String... filerValues) {
    clickFilterTab(category);

    expandedFilterTitle.shouldBe(Condition.visible, Condition.exactText(category));

    for (String filterValue : filerValues) {
      panel.scrollTo();
      expandedFilterOptionList.find(Condition.exactText(filterValue))
                              .shouldBe(Condition.visible, Condition.enabled)
                              .click();
    }

    expandedFilterTitle.should(Condition.not(Condition.exist));
  }

  public static void shouldBeDisplayed() {
    panel.shouldBe(Condition.visible);
  }

}
