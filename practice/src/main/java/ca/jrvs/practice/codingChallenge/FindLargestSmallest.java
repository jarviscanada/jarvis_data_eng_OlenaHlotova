package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.notion.so/jarvisdev/Find-Largest-Smallest-762505d138434dd38a5dfb88ef8d5ee4
 * Find Largest/Smallest
 */

public class FindLargestSmallest {

  /**
   * Requirements: Exact one for loop
   * O(n)
   */
  public int[] findLargestSmallest (int[] arr) {
    int largest = Integer.MIN_VALUE;
    int smallest = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > largest) {
        largest = arr[i];
      }
      if (arr[i] < smallest) {
        smallest = arr[i];
      }
    }
    int[] result = {largest,smallest};
    return result;
  }



  /**
   * Requirements: Use Stream APIs
   */

  public int[] findLargestSmallestStream(int[] arr) {
    int min = Arrays.stream(arr).min().getAsInt();
    int max = Arrays.stream(arr).max().getAsInt();
    int[] result = {max,min};
    return result;
  }

  /**
   * Requirements: Use Java built-in API
   */
  public int[]findLS(List<Integer> arr) {
    int largest = Collections.max(arr);
    int smallest = Collections.min(arr);
    int[] result = {largest,smallest};
    return result;
  }
}
