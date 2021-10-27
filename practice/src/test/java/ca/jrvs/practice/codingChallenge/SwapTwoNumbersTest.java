package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class SwapTwoNumbersTest {

  int x = 1;
  int y = 5;
  int[] res = {5,1};
  SwapTwoNumbers s = new SwapTwoNumbers();
  @Test
  public void swap() {
    int[] r1 = s.swap(x,y);
    assertEquals(Arrays.toString(r1), Arrays.toString(res));

  }

  @Test
  public void swapArithmetic() {
    int[] r1 = s.swapArithmetic(x,y);
    assertEquals(Arrays.toString(r1), Arrays.toString(res));
  }
}