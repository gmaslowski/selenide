package com.codeborne.selenide.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TextsInAnyOrderTest {

  @Test
  void testApplyWithSameOrder() {
    TextsInAnyOrder texts = new TextsInAnyOrder(asList("One", "Two", "Three"));
    testApplyMethod(true, texts);
  }

  private void testApplyMethod(boolean shouldMatch, TextsInAnyOrder texts) {
    WebElement mockElement1 = mock(WebElement.class);
    WebElement mockElement2 = mock(WebElement.class);
    WebElement mockElement3 = mock(WebElement.class);

    when(mockElement1.getText()).thenReturn("One");
    when(mockElement2.getText()).thenReturn("Two");
    when(mockElement3.getText()).thenReturn("Three");
    Assertions.assertEquals(shouldMatch, texts.apply(asList(mockElement1, mockElement2, mockElement3)));
  }

  @Test
  void testApplyWithDifferentOrder() {
    TextsInAnyOrder texts = new TextsInAnyOrder(asList("Two", "One", "Three"));
    testApplyMethod(true, texts);
  }

  @Test
  void testApplyWithWrongListSize() {
    TextsInAnyOrder texts = new TextsInAnyOrder(asList("Two", "One"));
    testApplyMethod(false, texts);
  }

  @Test
  void testApplyWithBiggerSize() {
    TextsInAnyOrder texts = new TextsInAnyOrder(asList("Two", "One", "four", "three"));
    testApplyMethod(false, texts);
  }

  @Test
  void testToString() {
    Assertions.assertEquals("TextsInAnyOrder [One, Two]", new TextsInAnyOrder(asList("One", "Two")).toString());
  }
}
