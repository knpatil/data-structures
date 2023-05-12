package heap;

import java.util.Arrays;
import java.util.Random;

public class MaxHeap {

    private final int[] heap;    // array to store the heap elements
    private int size;            // current number of elements in the heap
    private final int capacity;  // maximum number of elements the heap can hold

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1]; // add an extra element for the sentinel node
        this.heap[0] = Integer.MAX_VALUE;  // set the sentinel node to the maximum possible value
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
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // remove and return the maximum element from the heap
    public int extractMax() {
        if (size == 0) {
            // throw an exception if the heap is empty
            throw new RuntimeException("Heap is empty, cannot extract maximum element.");
        }

        int max = heap[1];  // store the maximum element to be returned
        heap[1] = heap[size];  // move the last element to the root
        size--;
        sink();  // restore the heap property starting from the root

        return max;
    }

    // restore the heap property by sinking a node down the heap
    private void sink() {
        int k = 1; // we always sync the root, as that was extracted & swapped by last element
        // keep sinking the node until it is greater than both its children or becomes a leaf node
        while (k <= size / 2) {
            int left = leftChild(k);     // Calculate the index of the left child
            int right = rightChild(k);   // Calculate the index of the right child
            int maxIndex = k;            // Initialize the index of the largest element to the current element

            // Check if the left child is larger than the current element
            if (left <= size && heap[left] > heap[maxIndex]) {
                maxIndex = left;
            }

            // Check if the right child is larger than the current element
            if (right <= size && heap[right] > heap[maxIndex]) {
                maxIndex = right;
            }

            // If the largest element is not the current element, swap the current element with the largest element
            if (maxIndex != k) {
                swap(k, maxIndex);
                k = maxIndex;
            } else {
                break;      // we are done if k already in the right position, it is larger than its children
            }
        }
    }

    private static void printHeap(int[] heap, int size) {
        int maxDepth = (int) (Math.log(size) / Math.log(2));  // log base 2 of n

        StringBuilder hs = new StringBuilder();  // heap string builder
        for (int d = maxDepth; d >= 0; d--) {  // number of layers, we build this backwards
            int layerLength = (int) Math.pow(2, d);  // numbers per layer

            StringBuilder line = new StringBuilder();  // line string builder
            for (int i = layerLength; i < (int) Math.pow(2, d + 1); i++) {
                // before spaces only on not-last layer
                if (d != maxDepth) {
                    line.append(" ".repeat((int) Math.pow(2, maxDepth - d)));
                }
                // extra spaces for long lines
                int loops = maxDepth - d;
                if (loops >= 2) {
                    loops -= 2;
                    while (loops >= 0) {
                        line.append(" ".repeat((int) Math.pow(2, loops)));
                        loops--;
                    }
                }

                // add in the number
                if (i <= size) {
                    line.append(String.format("%-2s", heap[i]));  // add leading zeros
                } else {
                    line.append("--");
                }

                line.append(" ".repeat((int) Math.pow(2, maxDepth - d)));  // after spaces
                // extra spaces for long lines
                loops = maxDepth - d;
                if (loops >= 2) {
                    loops -= 2;
                    while (loops >= 0) {
                        line.append(" ".repeat((int) Math.pow(2, loops)));
                        loops--;
                    }
                }
            }
            hs.insert(0, line.toString() + "\n");  // prepend line
        }
        System.out.println(hs.toString());
    }


    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(20);
        Random rand = new Random();
        for (int i = 1; i <= 20; i++) {
            int number = rand.nextInt(99) + 1; // generate random number from 1 to 20
            heap.insert(number);
            //heap.printHeap();
        }
        System.out.println(Arrays.toString(heap.heap));
        printHeap(heap.heap, heap.size);
    }
}


/*

heap[0] = Integer.MAX_VALUE;

The reason for doing this is to simplify the code for the sink() method, which is used to move a node down the heap to its correct position.

By setting heap[0] to the maximum value, we can assume that any node in the heap will always have a parent that has a larger value. This allows us to avoid checking if the current node is at the root of the heap in the sink() method, and simplifies the code.

For example, if we did not set heap[0] = Integer.MAX_VALUE, then we would need to check if the current node is at the root of the heap in the sink() method, which would require extra conditional statements and make the code more complex.

Therefore, initializing heap[0] to Integer.MAX_VALUE simplifies the code and makes it easier to understand and maintain.

 */