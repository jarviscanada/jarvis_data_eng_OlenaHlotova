package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;


/**
 * https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-6c084c46cc48406692d98612e54a0ae7
 * Stack using queue
 */

/**
 * 2 queues
 */
class Stack {

  Queue<Integer> q1;
  Queue<Integer> q2;

  public Stack() {
    this.q1 = new LinkedList<Integer>();
    this.q2 = new LinkedList<Integer>();
  }

  public void push(int x) {
    q1.add(x);
  }

  public int pop() {
    while (q1.size() > 1) {
      int i = q1.poll();
      q2.add(i);
    }
    int n = q1.poll();
    q1 = new LinkedList<>(q2);
    q2 = new LinkedList<>();
    return n;
  }

  public int top() {
    while (q1.size() > 1) {
      int i = q1.poll();
      q2.add(i);
    }
    int n = q1.peek();
    q2.add(q1.poll());
    q1 = new LinkedList<>(q2);
    q2 = new LinkedList<>();
    return n;
  }

  public boolean empty() {
    return q1.isEmpty();
  }

  public String toString() {
    return q1.toString();
  }
  public static void main(String[] args) {
    Stack obj = new Stack();
    System.out.println(obj);
    obj.push(1);
    System.out.println(obj);
    obj.push(2);
    System.out.println(obj);
    int p = obj.top();
    System.out.println(obj + " top "+ p);
    int l = obj.pop();

    System.out.println(l + " popped");

    boolean b = obj.empty();
    System.out.println(b+" empty "+obj);
  }
}

/**
 * 1 queue
 */
class StackOneQueue {
  Queue<Integer> q1;
  public StackOneQueue() {
    this.q1 = new LinkedList<Integer>();
  }
  public void push(int x) {
    q1.add(x);
  }
  public boolean empty() {
    return q1.isEmpty();
  }
  public String toString(){
    return q1.toString();
  }

  public int pop() {
    int size = q1.size();
    for (int i = 1; i<size; i++){
      q1.add(q1.poll());
    }
    int result = q1.poll();
    return result;
  }

  public int top() {
    int size = q1.size();
    for (int i = 1; i<size; i++){
      q1.add(q1.poll());

    }
    int result = q1.peek();
    q1.add(result);
    q1.poll();
    return result;
  }


  public static void main(String[] args) {
    StackOneQueue obj = new StackOneQueue();
    System.out.println(obj);
    obj.push(1);
    System.out.println(obj);
    obj.push(2);
    System.out.println(obj);
    int p = obj.top();
    System.out.println(obj + " top "+ p);
    int l = obj.pop();

    System.out.println(l + " popped");

    boolean b = obj.empty();
    System.out.println(b+" empty "+obj);
  }
}
