package ca.jrvs.practice.codingChallenge;

import java.util.stream.IntStream;

/**
 * https://www.notion.so/jarvisdev/Missing-Number-c4bedc6528ed4a179d1747d305ef6718
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 */

public class MissingNumber {

  /**
   * Sum all number
   * O(n)
   */
  public int missingNumber(int[] arr) {
    return 0;
  }

  public static void main(String[] args) {
    MissingNumber m = new MissingNumber();
    System.out.println(m.missingNumberG(new int[]{3,0,1}));
  }
  /**
   * using Set
   * O(n)
   */
  public int missingNumberSet(int[] nums) {
    int n = nums.length;
    return n;

  }

  /**
   * Gauss' Formula
   * O(n)
   */
  public int missingNumberG(int[] arr) {
    int sum = 0;
    int idx = -1;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 0) {
        idx = i;
      }
      else {
        sum += arr[i];
      }
    }
    int total = (arr.length + 1) * arr.length / 2;
    return total-sum;

  }

}
