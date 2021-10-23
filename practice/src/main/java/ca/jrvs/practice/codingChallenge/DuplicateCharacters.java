package ca.jrvs.practice.codingChallenge;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Duplicate Characters
 * https://www.notion.so/jarvisdev/Duplicate-Characters-1181a83968434c5ca9a0686bbc573f1c
 * O(n)
 */

public class DuplicateCharacters {

  public Set isDuplicate(String s) {
    s = s.toLowerCase();
    s = s.replaceAll("[^a-zA-Z0-9]", "");
    char[] chars = s.toCharArray();
    Map <Character, Integer> map = new HashMap();
    for (Character c: chars) {
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      }else{
        map.put(c, 1);
      }
    }
    Set set = new HashSet();
    Set <Character> keys = map.keySet();
    for (Character k:keys) {
      if (map.get(k) > 1) {
        set.add(k);
      }
    }
    return set;
  }

//  public static void main(String[] args) {
//    System.out.println(isDuplicate("A blck t"));
//  }

}
