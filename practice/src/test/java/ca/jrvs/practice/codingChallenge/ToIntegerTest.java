package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToIntegerTest {
  String s1 = "1";
  String s2 = "-2";
  String s3 = "nnn";
  String s4 = "0";
  String s5 = "1,9";
  String s6 = "0123";
  String s7 = "-0123";
  String error = "Invalid Input";
  Integer myInt1;
  Integer myInt3;
  Integer myInt4;
  Integer myInt5;
  Integer myInt6;
  Integer myInt7;
  @Test
  public void toInt() {
    myInt1 = ToInteger.toInt(s1);
    assertEquals(myInt1, 1,0);
    Integer myInt2 = ToInteger.toInt(s2);
    assertEquals(myInt2, -2,0);
    try{
      myInt3 = ToInteger.toInt(s3);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    myInt4 = ToInteger.toInt(s4);
    assertEquals(myInt4, 0,0);
    try {
      myInt5 = ToInteger.toInt(s5);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    myInt6 = ToInteger.modInt(s6);
    assertEquals(myInt6, 123, 0);
    myInt7 = ToInteger.modInt(s7);
    assertEquals(myInt7, -123, 0);

  }

  @Test
  public void stringToint() {
    myInt1 = ToInteger.modInt(s1);
    assertEquals(myInt1, 1, 0);
    Integer myInt2 = ToInteger.modInt(s2);
    assertEquals(myInt2, -2, 0);
    try {
      myInt3 = ToInteger.modInt(s3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    myInt4 = ToInteger.modInt(s4);
    assertEquals(myInt4, 0, 0);
    try {
      myInt5 = ToInteger.modInt(s5);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    myInt6 = ToInteger.modInt(s6);
    assertEquals(myInt6, 123, 0);
    myInt7 = ToInteger.modInt(s7);
    assertEquals(myInt7, -123, 0);
  }
}