package stack.fixed;

public class Stack {
    int[] stack;
    int top;

    // Initialize the stack with a fixed size
    public Stack(int capacity) {
        stack = new int[capacity];
        top = -1;
    }

    // Push an element to the top of the stack
    public void push(int element) {
        if (top < stack.length - 1) {
            top++;
            stack[top] = element;
        } else {
            System.out.println("Stack is full!");
        }
    }

    // Pop an element from the top of the stack
    public int pop() {
        if (top >= 0) {
            int element = stack[top];
            top--;
            return element;
        } else {
            System.out.println("Stack is empty!");
            return -1;
        }
    }

    // Get the top element of the stack
    public int peek() {
        if (top >= 0) {
            return stack[top];
        } else {
            System.out.println("Stack is empty!");
            return -1;
        }
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
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
        Stack myStack = new Stack(5);
        System.out.println("No of elements: " + myStack.size());
        myStack.push(7);
        myStack.push(3);
        myStack.push(4);
        myStack.print();
        System.out.println("No of elements: " + myStack.size());
        System.out.println("Top element: " + myStack.peek());
        System.out.println("Popped element: " + myStack.pop());
        System.out.println("Popped element: " + myStack.pop());
        System.out.println("Is my stack empty? " + myStack.isEmpty());
        System.out.println("No of elements: " + myStack.size());
        myStack.print();
    }
}
