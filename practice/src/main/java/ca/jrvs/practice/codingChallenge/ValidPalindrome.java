package ca.jrvs.practice.codingChallenge;

/**
 * Valid Palindrome
 * https://www.notion.so/jarvisdev/Valid-Palindrome-848178367e0a40618e4b5f5abdc955d0
 */

public class ValidPalindrome {

  /**
   * Requirements: Two pointers
   * O(n)
   */

  public boolean isPalindrome(String s) {
    if(s==null){
      return false;
    }
    s = s.toLowerCase();
    s = s.replaceAll("[^a-zA-Z0-9]", "");
    int left = 0;
    int right = s.length()-1;
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }



  /**
   * Requirements: recursion
   * O(n)
   */

  public boolean recursiveIsPalindrome(String s) {
    s = s.toLowerCase();
    s = s.replaceAll("[^a-zA-Z0-9]", "");
    return isValidPalindrome(s);
  }

  private boolean isValidPalindrome(String s) {
    if(s==null){
      return false;
    }
    int left = 0;
    int right = s.length()-1;
    if (s.length() == 0 || s.length() ==1) {
      return true;
    }
    // Removing first and last character
    // of a string using substring() method
    if (s.charAt(left) == s.charAt(right)) {
      String newString = s.substring(1, s.length() - 1);
      return isValidPalindrome(newString);
    }return false;
  }
}
