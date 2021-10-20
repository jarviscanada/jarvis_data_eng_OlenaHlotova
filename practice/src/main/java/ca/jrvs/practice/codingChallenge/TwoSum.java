package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 * https://www.notion.so/jarvisdev/Two-Sum-256671c8775f4f50944943a6f021a578
 */

public class TwoSum {

  /**
   * brute force (two loop)
   * O(n^2)
   */
  public static int[] sum(int[] nums, int target) {
    if (nums == null || nums.length < 2){
      throw new IllegalArgumentException("Invalid Input");}
    for (int i = 0; i < nums.length; i++){
      for (int n = i+1; n < nums.length; n++){
        if (nums[i]+nums[n] == target) {
          return new int[] {i,n};
        }
      }
    }
    return new int[0];
  }

  /**
   *  Use hashmap
   *  O(n)
   */
  public static int[] sum2(int[] nums, int target) {
    if (nums == null || nums.length < 2) {
      throw new IllegalArgumentException("Invalid Input");
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++){
      int diff = target - nums[i];
      if (map.containsKey(diff)) {
        return new int[] {map.get(diff), i};
      }else {
        map.put(nums[i], i);
      }
    }
    return new int[0];
  }

  /**
   * Use sorting
   * O(n*log(n))
   */

  public static int[] sumSort(int[] nums, int target) {
    if (nums == null || nums.length < 2) {
      throw new IllegalArgumentException("Invalid Input");
    }
    Arrays.sort(nums);
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      if (nums[left] + nums[right] == target) {
        return new int[]{nums[left], nums[right]};
      } else if (nums[left] + nums[right] < target) {
        left++;
      } else {
        right--;
      }
    }
    return new int[]{};

  }
}
