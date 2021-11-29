package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Print-letter-with-number-2bdacf543eb842a5b1539bf61541385d
 * Print letter with Number
 * O()
 */

public class LetterWithNumber {

  public static String printLetterNumber(String s) {
    if (s == null || s.isEmpty())
      return "";
    s = s.replaceAll("[^a-zA-Z]", "");

    char[] chars = new char[s.length() *3];

    for (int i = 0; i < chars.length; i++) {
      if (chars[i] >= 65 && chars[i] <= 90) {
        int n = chars[i] - 16;

        chars[i+1] = (char)n;
      }
      if (chars[i] >= 97 && chars[i] <= 122) {
        int m = chars[i] - 48;
        chars[i+1] = (char)m;
      }
      if (chars[i] >= 48 && chars[i] <= 57) {
        continue;
      }
    }

    String result = String.valueOf(chars);
    return result;
  }

}
