package com.codeborne.selenide.collections;

import com.codeborne.selenide.ex.ListSizeMismatch;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SizeGreaterThanTest {

  @Test
  void testApplyWithEmptyList() {
    assertFalse(new SizeGreaterThan(10).apply(emptyList()));
  }

  @Test
  void testApplyWithWrongSizeList() {
    assertFalse(new SizeGreaterThan(10).apply(singletonList(mock(WebElement.class))));
  }

  @Test
  void testApplyWithCorrectSizeGreaterThan() {
    assertTrue(new SizeGreaterThan(1).apply(asList(mock(WebElement.class), mock(WebElement.class))));
  }

  @Test
  void testFailMethod() {
    WebElementsCollection mockedWebElementCollection = mock(WebElementsCollection.class);
    when(mockedWebElementCollection.description()).thenReturn("Collection description");

    try {
      new SizeGreaterThan(10).fail(mockedWebElementCollection,
        emptyList(),
        new Exception("Exception message"),
        10000);
    } catch (ListSizeMismatch ex) {
      assertEquals(": expected: > 10, actual: 0, collection: Collection description\n" +
        "Elements: []", ex.getMessage());
    }
  }

  @Test
  void testToString() {
    assertEquals("size > 10", new SizeGreaterThan(10).toString());
  }
}
