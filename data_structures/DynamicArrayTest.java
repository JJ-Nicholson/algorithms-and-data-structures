import java.util.*;

public class DynamicArrayTest {
  public static void main(String[] args) {
    DynamicArray<Integer> a = new DynamicArray<>(2);

    // basic add / resize
    System.out.println("Add:");
    for (int i = 0; i < 5; i++) {
      a.add(i);
      System.out.println("  " + a + " size=" + a.size() +
                         " cap=" + a.capacity());
    }

    // indexed add (shift right)
    System.out.println("\nAdd at index 2:");
    a.add(2, 99);
    System.out.println("  " + a);

    // get / set
    System.out.println("\nGet / set:");
    System.out.println("  get(2) = " + a.get(2));
    a.set(2, 42);
    System.out.println("  after set: " + a);

    // remove by index (shift left)
    System.out.println("\nRemove index 3:");
    a.remove(3);
    System.out.println("  " + a);

    // remove by value
    System.out.println("\nRemove value 42:");
    a.remove(Integer.valueOf(42));
    System.out.println("  " + a);

    // contains / indexOf / lastIndexOf
    System.out.println("\nSearch:");
    System.out.println("  contains 4 = " + a.contains(4));
    System.out.println("  indexOf 4 = " + a.indexOf(4));
    a.add(4);
    System.out.println("  lastIndexOf 4 = " + a.lastIndexOf(4));
    System.out.println("  " + a);

    // iterator + fail-fast
    System.out.println("\nIterator:");
    try {
      for (Integer x : a) {
        if (x == 1)
          a.add(100); // should throw
      }
    } catch (ConcurrentModificationException e) {
      System.out.println("  fail-fast OK");
    }

    // clear
    System.out.println("\nClear:");
    a.clear();
    System.out.println("  " + a + " size=" + a.size());
  }
}
