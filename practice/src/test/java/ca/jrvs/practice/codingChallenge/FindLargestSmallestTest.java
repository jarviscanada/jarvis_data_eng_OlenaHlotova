package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class FindLargestSmallestTest {

  FindLargestSmallest f = new FindLargestSmallest();
  int[] arr = {1,2,3,4,5};
  int[] arr2 = {0,-1,-2,-3,-4,-5};

  int[] res1 = {5,1};
  int[] res2 = {0,-5};
  @Test
  public void findLargestSmallest() {
    int[] r1 = f.findLargestSmallest(arr);
    int[] r2 = f.findLargestSmallest(arr2);

    assertEquals(Arrays.toString(f.findLargestSmallest(res1)), Arrays.toString(r1));
    assertEquals(Arrays.toString(f.findLargestSmallest(res2)), Arrays.toString(r2));
  }

  public void findLargestSmallestStream() {
    int[] r1 = f.findLargestSmallestStream(arr);
    int[] r2 = f.findLargestSmallestStream(arr2);

    assertEquals(Arrays.toString(f.findLargestSmallest(res1)), Arrays.toString(r1));
    assertEquals(Arrays.toString(f.findLargestSmallest(res2)), Arrays.toString(r2));
  }

  public void findLS() {
    List<Integer> l1 = Arrays.asList(1,2,3,4,5);
    List<Integer> l2 = Arrays.asList(0,-1,-2,-3,-4,-5);
    int[] r1 = f.findLS(l1);
    int[] r2 = f.findLS(l2);

    assertEquals(Arrays.toString(f.findLargestSmallest(res1)), Arrays.toString(r1));
    assertEquals(Arrays.toString(f.findLargestSmallest(res2)), Arrays.toString(r2));
  }
}