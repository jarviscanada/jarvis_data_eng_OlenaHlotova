package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidPalindromeTest {
  String s1 = "b";
  String s2 = "ab";
  String s3 = "bb";
  String s4 = "Aba";
  String s5 = "A man, a plan, a canal: Panama";
  String s6 = "race a car";
  String s7 = "b3";
  String s8 = "asd f sa";

  @Test
  public void isPalindrome() {
    ValidPalindrome p = new ValidPalindrome();
    assertTrue(p.isPalindrome(s1));
    assertFalse(p.isPalindrome(s2));
    assertTrue(p.isPalindrome(s3));
    assertTrue(p.isPalindrome(s4));
    assertTrue(p.isPalindrome(s5));
    assertFalse(p.isPalindrome(s6));
    assertFalse(p.isPalindrome(s7));
    assertFalse(p.isPalindrome(s8));
   }

  @Test
  public void recursiveIsPalindrome() {
    ValidPalindrome p = new ValidPalindrome();
    assertTrue(p.recursiveIsPalindrome(s1));
    assertFalse(p.recursiveIsPalindrome(s2));
    assertTrue(p.recursiveIsPalindrome(s3));
    assertTrue(p.recursiveIsPalindrome(s4));
    assertTrue(p.recursiveIsPalindrome(s5));
    assertFalse(p.recursiveIsPalindrome(s6));
    assertFalse(p.recursiveIsPalindrome(s7));
    assertFalse(p.recursiveIsPalindrome(s8));
  }
}