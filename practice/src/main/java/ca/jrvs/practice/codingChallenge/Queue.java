package ca.jrvs.practice.codingChallenge;

import java.util.Stack;


/**
 * implement Queue with stack
 * https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-1ac1b05f15e6434f85f601861b4d072e
 * Requirements: (Two Stacks) Push - O(n)O(n) per operation, Pop - O(1)O(1) per operation
 */
class MyQueue {

  Stack s1 = new Stack();
  Stack s2 = new Stack();
  private int front;
  public MyQueue() {

  }

// Big-O: push O(n)
// Big-O: pop O(1)
  public void push(int x) {
    while (!s1.isEmpty()) {
      s2.push(s1.pop());
    }
    s1.push(x);
    while(!s2.isEmpty()) {
      s1.push(s2.pop());
    }
    s2 = new Stack();
  }

  public int pop() {
    return (int)s1.pop();
  }
  public int peek() {
    int res = (int) s1.peek();
    return res;
  }

// Big-O: push O(1)
// Big-O: pop O(n)
//    public void push(int x) {
//      s1.push(x);
//    }

//  public int pop() {
//    while (s1.size() > 1) {
//      s2.add(s1.pop());
//    }
//    int result = (int) s1.pop();
//    while (s2.size() != 0) {
//      s1.add(s2.pop());
//    }
//    s2 = new Stack();
//    return result;
//  }

//  public int peek() {
//    while (s1.size() > 1) {
//      s2.add(s1.pop());
//    }
//    int result = (int) s1.pop();
//    s1.add(result);
//    while (s2.size() != 0) {
//      s1.add(s2.pop());
//    }
//    s2 = new Stack();
//    return result;
//  }

  public boolean empty() {
    return s1.isEmpty();
  }
  public String toString(){
    return s1.toString();
  }


  /**
   * Requirements: (Two Stacks) Push - O(1)O(1) per operation, Pop - Amortized O(1)O(1) per operation
   */

  public void push2(int x) {
    //Time complexity : O(1).
    //space: O(n)
    if (s1.empty()) {
      front = x;
    }
    s1.push(x);
  }

  public int pop2() {
    //Time complexity: Amortized O(1), Worst-case O(n).
    //Space complexity : O(1).
    if (s2.empty()) {
      while (!s1.isEmpty()) {
        s2.push(s1.pop());
      }
    }
    int r = (int)s2.pop();
    return r;
  }

  public int peek2() {
    if (!s2.isEmpty()) {
      return (int)s2.peek();
    }
    return front;
  }

  public boolean empty2() {
    return s1.isEmpty() && s2.isEmpty();
  }


  public static void main(String[] args) {
    MyQueue obj = new MyQueue();
    System.out.println(obj);
    obj.push(1);
    System.out.println(obj);
    obj.push(2);
    System.out.println(obj);
    int p = obj.peek();
    System.out.println(obj + " peek " + p);
    int l = obj.pop();

    System.out.println(l + " popped");

    boolean b = obj.empty();
    System.out.println(b + " empty " + obj);
  }
}
