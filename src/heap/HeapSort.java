package heap;

import java.util.Arrays;

public class HeapSort {
    public static void sort(int[] arr) {
        int n = arr.length;

        // build a max-heap from the input array
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // repeatedly extract the maximum element from the heap and put it at the end of the array
        for (int i = n - 1; i >= 0; i--) {
            // swap the root of the heap with the last element of the heap
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // restore the heap property on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // restore the heap property starting from the given index k
    private static void heapify(int[] arr, int n, int k) {
        int largest = k;
        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // find the largest element among k and its two children
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // swap k with its largest child if necessary, and recursively heapify the subtree
        if (largest != k) {
            int temp = arr[k];
            arr[k] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 8, 3, 1, 6 };
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr)); // prints [1, 2, 3, 5, 6, 8]
    }
}
