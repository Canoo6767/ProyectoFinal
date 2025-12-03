import java.util.Arrays;

public class MiArrayList implements Cloneable {
    private int[] data;
    private int size;

    public MiArrayList(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public MiArrayList() {
        this(10);
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    public int get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return data[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        data[index] = value;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public void fromArray(int[] arr) {
        data = Arrays.copyOf(arr, arr.length);
        size = arr.length;
    }

    @Override
    public MiArrayList clone() {
        MiArrayList copy = new MiArrayList(size);
        copy.fromArray(this.toArray());
        return copy;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
