package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OddCheckTest {

  int i = 1;
  int a = 2;
  int b = -5;
  int z = 0;
  @Test
  public void oddEven() {
    String oddCheck = OddCheck.oddEven(i);
    assertTrue(i + "is: ",oddCheck == "odd");
    String oddCheck2 = OddCheck.oddEven(a);
    assertTrue(a + "is: ",oddCheck2 == "even");
    String oddCheck3 = OddCheck.oddEven(b);
    assertTrue(b + "is: ",oddCheck3 == "odd");
    String oddCheck4 = OddCheck.oddEven(z);
    assertTrue(z + "is: ",oddCheck4 == "even");

  }

  @Test
  public void oddEvenBit() {
    String oddCheck = OddCheck.oddEven(i);
    assertTrue(i + "is: ",oddCheck == "odd");
    String oddCheck2 = OddCheck.oddEven(a);
    assertTrue(a + "is: ",oddCheck2 == "even");
    String oddCheck3 = OddCheck.oddEven(b);
    assertTrue(b + "is: ",oddCheck3 == "odd");
    String oddCheck4 = OddCheck.oddEven(z);
    assertTrue(z + "is: ",oddCheck4 == "even");
  }
}