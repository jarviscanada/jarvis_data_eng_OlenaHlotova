package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class FibonacciTest {

  Fibonacci fibonacci = new Fibonacci();
  @Test
  public void fib() {
    int[] fiblist = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
    for (int n = 0; n < fiblist.length; n++){
      assertEquals(fiblist[n], fibonacci.fib(n));
    }
  }

  @Test
  public void fibDynamic() {
    int[] fiblist = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
    List<Integer> memo = new ArrayList<Integer>();
    for (Integer n = 0; n < fiblist.length; n++){
      assertEquals(fiblist[n], fibonacci.fibDynamic(n, memo));
    }

  }
}