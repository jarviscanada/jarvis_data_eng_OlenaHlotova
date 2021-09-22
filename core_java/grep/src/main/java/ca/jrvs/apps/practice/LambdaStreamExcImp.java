package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc{

  @Override
  public Stream<String> createStrStream(String... strings) {
    Stream<String> stream = Arrays.stream(strings);
    return stream;
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    Stream<String> stream = createStrStream(strings).map(letter -> letter.toUpperCase());
    return stream;
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    Stream<String> stream = stringStream.filter(element -> element != pattern);
    return stream;
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    IntStream intStream = Arrays.stream(arr);
    return intStream;
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    List<E> list = stream.collect(ArrayList::new, ArrayList::add,
        ArrayList::addAll);
    return list;
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    List<Integer> list = intStream.collect(ArrayList::new, ArrayList::add,
        ArrayList::addAll);
    return list;
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    IntStream intStream = IntStream.rangeClosed(start, end);
    return intStream;
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    DoubleStream doubleStream = intStream.asDoubleStream().map(Math::sqrt);
    return doubleStream;
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    IntStream intStream1 = intStream.filter(n->n%2 ==1);
    return intStream1;
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    Consumer<String> print = value -> {
      System.out.println(prefix+value+suffix);
    };
    return print;
  }

  @Override
  public void PrintMessages(String[] messages, Consumer<String> printer) {
    for (String msg : messages) {
      printer.accept(msg);
    }
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    IntStream ints = getOdd(intStream);
    ints.forEach(i->printer.accept(String.valueOf(i)));
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    // Solution 1: Use reduce to reduce all the lists of integers to a single list
    // and then convert it to a stream so we can square the values
    // return ints.reduce(new ArrayList<Integer>(), (firstList, secondList) -> {
    // List<Integer> result = new ArrayList<>(firstList);
    // result.addAll(new ArrayList<Integer>(secondList));
    // return result;
    // }).stream().map(value -> value * value);
    Function<Integer, Integer> square = x -> x * x;
    Stream<Integer> stream = ints.flatMap(List::stream).map(square);
    return stream;
  }
}
