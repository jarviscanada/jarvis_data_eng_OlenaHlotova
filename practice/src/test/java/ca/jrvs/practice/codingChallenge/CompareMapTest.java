package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareMapTest {
  private final Map <String, String> map = new HashMap<>();
  private final Map <String, String> map3 = new HashMap();
  private final CompareMap compareMaps = new CompareMap();

  @Test
  public void compareMaps() {
    map.put("key1", "element 1");
    map.put("key2", "element 2");
    map.put("key3", "element 3");


    map3.put("key1", "element 1");
    map3.put("key2", "element 2");
    map3.put("key3", "element 3");

    assertTrue((CompareMap.compareMaps(map, map3)));
  }

  @Test
  public void compareMapsNotEqual() {
    map.put("key4", "element 1");
    map.put("key2", "element 2");
    map.put("key3", "element 3");


    map3.put("key1", "element 1");
    map3.put("key2", "element 2");
    map3.put("key3", "element 3");

    assertFalse((CompareMap.compareMaps(map, map3)));
  }
}