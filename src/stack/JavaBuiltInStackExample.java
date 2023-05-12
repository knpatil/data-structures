package stack;

import java.util.Stack;

public class JavaBuiltInStackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        // Push elements to the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Print the top element of the stack
        System.out.println("Top element: " + stack.peek()); // Output: Top element: 3

        // Pop elements from the stack
        System.out.println("Popped element: " + stack.pop()); // Output: Popped element: 3
        System.out.println("Popped element: " + stack.pop()); // Output: Popped element: 2

        // Check if the stack is empty
        if (stack.empty()) {
            System.out.println("Stack is empty!");
        } else {
            System.out.println("Stack is not empty!"); // Output: Stack is not empty
        }

        System.out.println("Size of the stack : " + stack.size()); // Output: 1
    }
}

