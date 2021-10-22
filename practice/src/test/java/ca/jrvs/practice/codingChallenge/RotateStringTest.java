package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotateStringTest {

  String s1 = "abcde";
  String goal1 = "cdeab";
  String goal2 = "abced";
  String goal3 = "abcd";

  @Test
  public void rotateString() {

    boolean r1 = RotateString.rotateString(s1, goal1);
    assertTrue(r1);

    boolean r2 = RotateString.rotateString(s1, goal2);
    assertFalse(r2);

    boolean r3 = RotateString.rotateString(s1, goal3);
    assertFalse(r3);


  }
}