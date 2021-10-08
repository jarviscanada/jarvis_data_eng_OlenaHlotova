package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 *  Check if two maps are equal
 * https://www.notion.so/jarvisdev/How-to-compare-two-maps-b92112274b854fd7bcf7de32ec698ba7
 */
public class CompareMap {

  /**
   * Using Java Collection .equals API
   * Big O: O(n)
   */
  public static <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2) {
    return m1.equals(m2);
  }


}
