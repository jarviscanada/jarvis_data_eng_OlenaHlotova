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

  /**
   * Implement equals in HashJMap
   * Big O: O()
   */

  public static void main(String[] args)
  {
    Map<Integer, String> map1 = new HashMap<Integer, String>();
    Map<Integer, String> map2 = new HashMap<Integer, String>();

    // Mapping string values to int keys for map1
    map1.put(10, "Geeks");
    map1.put(15, "4");
    map1.put(20, "Geeks");
    map1.put(25, "Welcomes");
    map1.put(30, "You");

    // Mapping string values to int keys
    map2.put(10, "Geeks");
    map2.put(15, "4");
    map2.put(20, "Geeks");
    map2.put(25, "Welcomes");
    map2.put(30, "You");
    System.out.print(compareMaps(map1, map2));
  }
}
