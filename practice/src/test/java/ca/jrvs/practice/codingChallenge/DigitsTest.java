package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class DigitsTest {
  String s1 = "1234";
  String s2 = "123.000";
  Digits d = new Digits();
  @Test
  public void containsDigits() {
    assertTrue(d.containsDigits(s1));
    assertFalse(d.containsDigits(s2));


  }

  @Test
  public void isDigit() {
    assertTrue(d.isDigit(s1));
    assertFalse(d.isDigit(s2));
  }

  @Test
  public void isDigitRegex() {
    assertTrue(d.isDigitRegex(s1));
    assertFalse(d.isDigitRegex(s2));
  }
}