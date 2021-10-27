package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Swap-two-numbers-393d88d0e57c4bfeb51313174fe3d906
 * Swap two numbers without using the third variable
 */

import java.util.Arrays;

public class SwapTwoNumbers {

  /**
   * Requirements: Use bit-manipulation
   * O(1)
   */
  public int[] swap(int x, int y) {
    x = x ^ y;
    y = x ^ y;
    x = x ^ y;
    int[] result = {x,y};
    return result;
  }
  /**
   * Requirements: Use Arithmetic Operators
   * O(1)
   */
  public int[] swapArithmetic(int x, int y) {
    x = x + y;
    y = x - y;
    x = x - y;
    int[] result = {x,y};
    return result;
  }

  public static void main(String[] args) {
    int x = 1;
    int y = 5;
    SwapTwoNumbers s = new SwapTwoNumbers();
    System.out.println(Arrays.toString(s.swapArithmetic(x,y)));
  }
}
