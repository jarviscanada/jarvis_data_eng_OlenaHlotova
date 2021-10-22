package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Rotate-String-1984cc5d900941d48c29f100fdea389e
 * Rotate string
 * Time complexity: O(n^2)
 * Space Complexity: O(n)
 */
public class RotateString {

  public static boolean rotateString(String s, String goal) {
    if (s.length() == goal.length() && (s+s).contains(goal)) {
      return true;
    }
    return false;
  }
}
