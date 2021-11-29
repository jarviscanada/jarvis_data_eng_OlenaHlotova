package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class DuplicateCharactersTest {
  String s2;
  @Test
  public void isDuplicate() {
    DuplicateCharacters d = new DuplicateCharacters();
    s2 = "A black cat";
    Set ss = new HashSet();
    ss.add("a");
    ss.add("c");
    assertTrue(ss == d.isDuplicate(s2));
  }
}