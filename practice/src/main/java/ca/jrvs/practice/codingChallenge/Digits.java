package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-8821045aa87f40a3b9338b75ef901cc4
 * Check if a String contains only digits
 */
public class Digits {

  /**
   * ASCII
   * O(n)
   */
  public boolean containsDigits(String s) {
    char[] chars = s.toCharArray();
    for (int i = 0; i< chars.length; i++) {
      if (chars[i] < 48 || chars[i] > 57) {
        return false;
      }
    }
    return true;
  }

  /**
   * parseLong
   * O(n)
   */
  public boolean isDigit(String s) {
    try {
      Long.parseLong(s);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
  /**
   * regex
   * O(n)
   */
  public boolean isDigitRegex(String s) {
    if(!s.matches("\\d*")) {
      return false;
    }
    return true;
  }

}
