package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lambdatest {
  public static void main(String[] args) {
    LambdaStreamExc strStream = new LambdaStreamExcImp();
    String[] array = {"a", "b", "c", "d", "e"};
    Stream<String> words = Stream.of("All", "men", "are", "created", "equal");
    int[] ints = {1, 2, 3, 4, 5};
    //strStream.createStrStream(array).forEach(x -> System.out.println(x));
    //strStream.toUpperCase(array).forEach(name -> System.out.print(name + " "));
    //strStream.filter(strStream.createStrStream(array), "b").forEach(x -> System.out.println(x));
    IntStream intStream = strStream.createIntStream(ints);
    //intStream = intStream.limit(3);
    //System.out.println(intStream.sum());
    //strStream.toList(intStream).forEach(n -> System.out.println(n));
    //strStream.createIntStream(0,10).forEach(n -> System.out.println(n));
    //strStream.squareRootIntStream(intStream).forEach(n -> System.out.println(n));
    //strStream.getOdd(intStream).forEach(n -> System.out.println(n));
    LambdaStreamExc lse = new LambdaStreamExcImp();
    //Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
    //printer.accept("Message body");

    String[] messages = {"a","b", "c"};
    //lse.PrintMessages(messages, lse.getLambdaPrinter("msg:", "!") );
    //lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
    List<Integer> aa = new ArrayList<>();
    aa.add(1);
    aa.add(2);
    List<Integer> bb = new ArrayList<>();
    bb.add(3);
    bb.add(4);
    List<List<Integer>> tt = new ArrayList<>();
    tt.add(aa);
    tt.add(bb);

    Stream<List<Integer>> uu = tt.stream();
    lse.flatNestedInt(uu).forEach(n -> System.out.println(n));
    //lse.flatNestedInt(uu);
  }
}
