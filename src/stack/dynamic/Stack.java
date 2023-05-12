package stack.dynamic;

public class Stack {
    private int[] stack;
    private int top;
    private int capacity;

    // Initialize the stack with an initial capacity
    public Stack(int initialCapacity) {
        stack = new int[initialCapacity];
        top = -1;
        capacity = initialCapacity;
    }

    // Push an element to the top of the stack
    public void push(int element) {
        if (top == capacity - 1) {
            resize();
        }
        top++;
        stack[top] = element;
    }

    // Pop an element from the top of the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return stack[top--];
    }

    // Get the top element of the stack
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return stack[top];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Double the capacity of the stack
    private void resize() {
        System.out.println("Resizing the stack");
        int newCapacity = capacity * 2; // double the size
        int[] newStack = new int[newCapacity];
        for (int i = 0; i < capacity; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
        capacity = newCapacity;
    }

    // Number of elements in the stack
    public int size() {
        return top + 1;
    }

    // Display all elements in stack as a list (from top to bottom)
    public void print() {
        System.out.print("Stack contents: [");
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i]);
            if (i != 0)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Stack myStack = new Stack(2);
        System.out.println("No of elements: " + myStack.size());
        myStack.push(7);
        myStack.push(9);
        myStack.print();
        myStack.push(6);
        System.out.println("Top element: " + myStack.peek());
        myStack.print();
        System.out.println("Popped element: " + myStack.pop());
        System.out.println("Is my stack empty? " + myStack.isEmpty());
        System.out.println("No of elements: " + myStack.size());
        myStack.print();
    }
}

