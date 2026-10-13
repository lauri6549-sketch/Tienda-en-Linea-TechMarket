public class ArrayList<E> implements List<E> {
    private Object[] items;
    private int size;
    private int capacity;

    public ArrayList() {
        this.size = 0;
        this.capacity = 10;
        this.items = new Object[capacity];
    }

    protected void Expand() {
        Object[] aux = new Object[capacity + 10];
        System.arraycopy(items, 0, aux, 0, size);
        capacity += 10;
        items = aux;
    }

    protected boolean isFull() {
        return capacity == size;
    }

    @Override
    public void add(E e) {
        if (isFull()) {
            Expand();
        }
        items[size++] = e;
    }

    @Override
    public void add(E e, int index) {
        if (index >= 0 && index <= size) {
            if (isFull()) {
                Expand();
            }
            for (int i = size; i > index; i--) {
                items[i] = items[i - 1];
            }
            items[index] = e;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if (index >= 0 && index < size) {
            E aux = (E) items[index];
            for (int i = index; i < size - 1; i++) {
                items[i] = items[i + 1];
            }
            size--;
            return aux;
        }
        return null;
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < size) {
            return (E) items[index];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        capacity = 10;
        items = new Object[capacity];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}