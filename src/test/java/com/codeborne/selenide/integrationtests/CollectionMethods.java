package com.codeborne.selenide.integrationtests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.currentThread;
import static org.junit.Assert.assertEquals;

public class CollectionMethods {
  @Before
  public void openTestPageWithJQuery() {
    open(currentThread().getContextClassLoader().getResource("page_with_selects_without_jquery.html"));
  }

  @Test
  public void canUseSizeMethod() {
    assertEquals(1, $$(By.name("domain")).size());
    assertEquals(1, $$("#theHiddenElement").size());
    assertEquals(4, $$("#radioButtons input").size());
    assertEquals(4, $$(By.xpath("//select[@name='domain']/option")).size());
    assertEquals(0, $$(By.name("non-existing-element")).size());
    assertEquals(0, $$("#dynamic-content-container span").size());
  }

  @Test
  public void canCheckSizeOfCollection() {
    $$(By.name("domain")).shouldHaveSize(1);
    $$("#theHiddenElement").shouldHaveSize(1);
    $$("#radioButtons input").shouldHaveSize(4);
    $$(By.xpath("//select[@name='domain']/option")).shouldHaveSize(4);
    $$(By.name("non-existing-element")).shouldHaveSize(0);
    $$("#dynamic-content-container span").shouldHaveSize(0);
  }

  @Test @Ignore // TODO Improve method shouldHaveSize() to wait
  public void shouldWaitUntilCollectionGetsExpectedSize() {
    $$("#dynamic-content-container span").shouldHaveSize(1);
  }

  @Test
  public void userCanFilterOutMatchingElements() {
    $$("#multirowTable tr").shouldHaveSize(2);
    $$("#multirowTable tr").filterBy(text("Norris")).shouldHaveSize(1);
    $$("#multirowTable tr").filterBy(cssClass("inexisting")).shouldHaveSize(0);
  }

  @Test
  public void userCanExcludeMatchingElements() {
    $$("#multirowTable tr").shouldHaveSize(2);
    $$("#multirowTable tr").excludeWith(text("Chack")).shouldHaveSize(0);
    $$("#multirowTable tr").excludeWith(cssClass("inexisting")).shouldHaveSize(2);
  }

  @Test
  public void userCanFindMatchingElementFromList() {
    $$("#multirowTable tr").findBy(text("Norris")).shouldHave(text("Norris"));
  }
}
