package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidParenthesesTest {
  String s1 = "()";
  String s2 = "()[]{}";
  String s3 = "(]";
  String s4 = "([)]";
  String s5 = "{[]}";

  @Test
  public void isValid() {
    boolean check1 = ValidParentheses.isValid(s1);
    assertTrue(check1);
    boolean check2 = ValidParentheses.isValid(s2);
    assertTrue(check2);
    boolean check3 = ValidParentheses.isValid(s3);
    assertFalse(check3);
    boolean check4 = ValidParentheses.isValid(s4);
    assertFalse(check4);
    boolean check5 = ValidParentheses.isValid(s5);
    assertTrue(check5);

  }
}