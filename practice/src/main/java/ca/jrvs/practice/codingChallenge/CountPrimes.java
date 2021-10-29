package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Count-Primes-ce436e4baa1d4f638321291954534550
 * Count Primes
 * O(Nlog(logN))
 */

public class CountPrimes {

  public int count(int n) {
    if (n <= 2)
      return 0;

    // init an array to track prime numbers
    boolean[] primes = new boolean[n];
    for (int i = 2; i < n; i++)
      primes[i] = true;

    for (int i = 2; i <= Math.sqrt(n - 1); i++) {
      // or for (int i = 2; i <= n-1; i++) {
      if (primes[i]) {
        for (int j = i + i; j < n; j += i)
          primes[j] = false;
      }
    }

    int count = 0;
    for (int i = 2; i < n; i++) {
      if (primes[i])
        count++;
    }
    return count;
  }

}
