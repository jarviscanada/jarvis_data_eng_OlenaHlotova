package ca.jrvs.practice.codingChallenge;

import jdk.jfr.internal.JVM;
import jdk.jfr.internal.Logger;

/**
 * String to Integer (atoi)
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-a82696b90ec04432b2b7c0b32ebfdc1a
 */

public class ToInteger {

  /**
   * Java build-in parsing method Big-O: O(n)
   */
  public static Integer toInt(String str) {
    try {
      return Integer.parseInt(str);
    } catch (NumberFormatException exception) {
      // Output expected NumberFormatException.
      throw new IllegalArgumentException("Invalid Input", exception);
    } catch (Exception exception) {
      // Output unexpected Exceptions.
      throw new RuntimeException("Failed to execute", exception);
    }
  }

  public static Integer modInt(String s) {
    char[] chars = s.toCharArray();
    int multiplier = 1;
    Integer result = 0;
    boolean isNegative = (chars[0] == '-');
    if (isNegative) {chars[0] = '0';}
      for (int i = chars.length - 1; i >= 0; i--) {
          if (chars[i] >= 48 & chars[i] <= 57) {
            int myInt = chars[i] - '0';
            result += myInt * multiplier;
            multiplier *= 10;
          } else {
            throw new IllegalArgumentException("Invalid Input");
          }
        }

      if (isNegative) {result *= -1;}
      return result;

  }
}