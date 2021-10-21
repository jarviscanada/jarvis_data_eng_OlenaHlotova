package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {



  public static boolean isValid(String s) {
    Stack stack = new Stack();
    HashMap<Character, Character> map = new HashMap<Character, Character>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (map.containsKey(c)) {
        stack.push(c);
      } else if (map.containsValue(c)) {
        if (!stack.empty() && map.get(stack.peek()) == c) {
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return stack.empty();
  }


}
