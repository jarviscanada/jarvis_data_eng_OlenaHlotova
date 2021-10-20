package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {

  int[] ints1 = {3,2,4};
  int[] ints2 = {0};
  int[] ints3 = {};
  int[] ints4 = {-3,2,4};
  int target1 = 6;
  int target2 = 1;
  int target3 = 100;
  String error = "Invalid Input";
  int[] newInts = new int[2];
  int[] newInts2;
  int[] newInts3;
  int[] newInts4;
  int[] newInts5;

  @Test
  public void sum() {
    newInts = TwoSum.sum(ints1, target1);
    assertEquals(Arrays.toString(new int[]{1, 2}),Arrays.toString(newInts));
    newInts2 = TwoSum.sum(ints4, target2);
    assertEquals(Arrays.toString(new int[]{0,2}),Arrays.toString(newInts2));
    try {
      newInts3 = TwoSum.sum(ints2, target1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    try {
      newInts4 = TwoSum.sum(ints3, target1);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    newInts5 = TwoSum.sum(ints4, target3);
    assertEquals(Arrays.toString(new int[]{}),Arrays.toString(newInts5));
  }

  @Test
  public void sum2() {
    newInts = TwoSum.sum(ints1, target1);
    assertEquals(Arrays.toString(new int[]{1, 2}),Arrays.toString(newInts));
    newInts2 = TwoSum.sum(ints4, target2);
    assertEquals(Arrays.toString(new int[]{0,2}),Arrays.toString(newInts2));
    try {
      newInts3 = TwoSum.sum(ints2, target1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    try {
      newInts4 = TwoSum.sum(ints3, target1);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    newInts5 = TwoSum.sum(ints4, target3);
    assertEquals(Arrays.toString(new int[]{}),Arrays.toString(newInts5));
  }

  @Test
  public void sumSort() {
    newInts = TwoSum.sum(ints1, target1);
    assertEquals(Arrays.toString(new int[]{1, 2}),Arrays.toString(newInts));
    newInts2 = TwoSum.sum(ints4, target2);
    assertEquals(Arrays.toString(new int[]{0,2}),Arrays.toString(newInts2));
    try {
      newInts3 = TwoSum.sum(ints2, target1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    try {
      newInts4 = TwoSum.sum(ints3, target1);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(error, e.getMessage());
    }
    newInts5 = TwoSum.sum(ints4, target3);
    assertEquals(Arrays.toString(new int[]{}),Arrays.toString(newInts5));

  }
}