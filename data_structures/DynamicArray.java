import java.util.*;

@SuppressWarnings("unchecked")

public class DynamicArray<E> extends AbstractList<E> {

  private E[] arr;
  private int length = 0;
  private int capacity = 0;

  public DynamicArray() { this(10); }

  public DynamicArray(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Illegal Capacity: " + capacity + ".");
    }

    this.capacity = capacity;
    arr = (E[]) new Object[capacity];
  }

  public int capacity() { return capacity; }

  @Override
  public int size() {
    return length;
  }

  /*
  @Override
  public boolean isEmpty() {
    return length == 0;
  } **/

  @Override
  public E get(int index) {
    rangeCheck(index);
    return arr[index];
  }

  @Override
  public E set(int index, E element) {
    rangeCheck(index);
    E old = arr[index];
    arr[index] = element;
    return old;
  }

  /*
  @Override
  public boolean add(E element) {
    add(length, element);
    return true;
  }**/

  @Override
  public void add(int index, E element) {
    rangeCheckForAdd(index);

    if (length == capacity) {
      resize();
    }

    for (int i = length; i > index; i--) {
      arr[i] = arr[i - 1];
    }

    arr[index] = element;
    length++;
    modCount++;
  }

  @Override
  public E remove(int index) {
    rangeCheck(index);

    E removed = arr[index];

    for (int i = index; i < length - 1; i++) {
      arr[i] = arr[i + 1];
    }

    arr[--length] = null;
    modCount++;

    return removed;
  }

  public boolean remove(Object o) {
    for (int i = 0; i < length; i++) {
      if (Objects.equals(o, arr[i])) {
        remove(i);
        return true;
      }
    }
    return false;
  }

  /*
  @Override
  public void clear() {
    for (int i = 0; i < length; i++) {
      arr[i] = null;
    }

    length = 0;
    modCount++;
  } **/

  public boolean contains(Object o) { return indexOf(o) != -1; }

  /*
  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < length; i++) {
      if (Objects.equals(o, arr[i]))
        return i;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    for (int i = length - 1; i >= 0; i--) {
      if (Objects.equals(o, arr[i]))
        return i;
    }
    return -1;
  } **/

  private void rangeCheckForAdd(int index) {
    if (index < 0 || index > length) {
      throw new IndexOutOfBoundsException(
          "Index: " + index + " is invalid for array of length: " + length +
          ".");
    }
  }

  private void rangeCheck(int index) {
    if (index < 0 || index >= length) {
      throw new IndexOutOfBoundsException(
          "Index: " + index + " is invalid for array of length: " + length +
          ".");
    }
  }

  private void resize() {
    capacity = (capacity == 0) ? 1 : 2 * capacity;
    E[] newArr = (E[]) new Object[capacity];
    for (int i = 0; i < length; i++) {
      newArr[i] = arr[i];
    }
    arr = newArr;
  }
}
