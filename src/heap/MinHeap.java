package heap;

public class MinHeap {
    private final int[] heap;   // array to store the heap elements
    private int size;     // current number of elements in the heap
    private final int capacity; // maximum number of elements the heap can hold

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1]; // add an extra element for the sentinel node
        this.heap[0] = Integer.MIN_VALUE; // set the sentinel node to the minimum possible value
    }

    // get the parent index of a given node index
    private int parent(int index) {
        return index / 2;
    }

    // get the left child index of a given node index
    private int leftChild(int index) {
        return index * 2;
    }

    // get the right child index of a given node index
    private int rightChild(int index) {
        return index * 2 + 1;
    }

    // swap two elements in the heap array
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // add a new element to the heap
    public void insert(int element) {
        if (size >= capacity) {
            // throw an exception if the heap is full
            throw new RuntimeException("Heap is full, cannot insert more elements.");
        }

        size++;
        heap[size] = element; // add the new element to the end of the heap
        int current = size;

        // keep swapping the new element with its parent until the heap property is restored
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // remove and return the minimum element from the heap
    public int extractMin() {
        if (size == 0) {
            // throw an exception if the heap is empty
            throw new RuntimeException("Heap is empty, cannot extract minimum element.");
        }

        int min = heap[1];  // store the minimum element to be returned
        heap[1] = heap[size];  // move the last element to the root
        size--;
        sink(1);  // restore the heap property starting from the root

        return min;
    }

    // restore the heap property by sinking a node down the heap
    private void sink(int k) {
        // keep sinking the node until it is smaller than both its children or becomes a leaf node
        while (k <= size / 2) {
            int left = leftChild(k);
            int right = rightChild(k);
            int minIndex = k;

            if (left <= size && heap[left] < heap[minIndex]) {
                minIndex = left;
            }

            if (right <= size && heap[right] < heap[minIndex]) {
                minIndex = right;
            }

            if (minIndex != k) {
                swap(k, minIndex);
                k = minIndex;
            } else {
                break;
            }
        }
    }
}
