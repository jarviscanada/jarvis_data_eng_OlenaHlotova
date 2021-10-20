package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Fibonacci Number/Climbing Stairs
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-6e3c2a0922694e18af9566c5fd844d7c
 * Big-O: O(2^n)
 */
public class Fibonacci {
  static List<Integer> memo = new ArrayList<>();

  //F(n) = F(n-1) + F(n-2)
  static int fib(int n) {
    if (n <= 1) {
      return n;
    }return fib(n-1) + fib(n-2);

  }

  /**
   * Fibonacci Number/Climbing Stairs
   * Memoized solution
   * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-6e3c2a0922694e18af9566c5fd844d7c
   * T(n) = #calls * time = (2n +1) * O(1) = O(2n+1)=O(n)
   * Big-O: O(n)
   */
  static int fibDynamic(Integer n, List<Integer> memo) {
    //Memoized solution
    int result;
    if (memo.size() < n+1) {
      for (int i = 0; i< n+1; i++){
      memo.add(null);}
    }
    if (memo.get(n) != null) {
      return memo.get(n);
    }if (n <=1) {
      return n;
    } else {
      result = fibDynamic(n-1, memo) + fibDynamic(n-2, memo);
      memo.set(n, result);
      return result;
    }
  }
}
