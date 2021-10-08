package ca.jrvs.practice.codingChallenge;
/**
 *  Check if a number is even or odd
 * https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-1e7d89bfee17414987a25dc22cfabb10
*/

public class OddCheck {


  /**
   * modulo
   * Big-O: O(1)
   * Justification: it's an arithmetic operation
   */
  public static String oddEven(int i) {
    return i % 2 ==0 ? "even" : "odd";
  }

  /**
   * bit XOR operator
   * Big-O: O(1)
   */
  public static String oddEvenBit(int i) {
    return ((i ^ 1) == (i+1)) ? "even" : "odd";
  }

  public static void main(String[] args)
  {
    int n = 101;
    System.out.print(oddEven(n));
  }
}
