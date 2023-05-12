package stack.generic;

public class Stack<T> {
    private T[] stack;
    private int top;
    private int capacity;

    // Initialize the stack with an initial capacity
    public Stack(int initialCapacity) {
        stack = (T[]) new Object[initialCapacity];
        top = -1;
        capacity = initialCapacity;
    }

    // Push an element to the top of the stack
    public void push(T element) {
        if (top == capacity - 1) {
            resize();
        }
        top++;
        stack[top] = element;
    }

    // Pop an element from the top of the stack
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow");
            return null;
        }
        return stack[top--];
    }

    // Get the top element of the stack
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return stack[top];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Double the capacity of the stack
    private void resize() {
        int newCapacity = capacity * 2;
        T[] newStack = (T[]) new Object[newCapacity];
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
        Stack<Integer> integerStack = new Stack<>(2);
        integerStack.push(8);
        integerStack.push(5);
        integerStack.print();
        System.out.println("Popped element: " + integerStack.pop());
        integerStack.push(2);
        integerStack.push(9);
        integerStack.print();

        Stack<String> bookStack = new Stack<>(2);
        bookStack.push("The Hobbit");
        bookStack.push("Da Vinci Code");
        bookStack.print();
        bookStack.push("Atomic Habits");
        bookStack.print();
        System.out.println("No of elements: " + bookStack.size());
        System.out.println("Popped book: " + bookStack.pop());
    }
}
