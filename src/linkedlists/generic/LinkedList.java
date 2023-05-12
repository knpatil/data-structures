package linkedlists.generic;

public class LinkedList<T> {
    Node<T> head;

    // add new node at the front of the list
    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        newNode.next = head;
        head = newNode;
    }

    // delete based on the value, only if exists
    public void delete(T data) {
        Node<T> curr = head;
        Node<T> prev = null;
        while (curr != null) {
            if (curr.data.equals(data)) {
                if (prev != null) {
                    prev.next = curr.next;
                } else {
                    head = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void printList() {
        Node<T> curr = head;
        System.out.print("List contains: [");
        while (curr != null) {
            System.out.print(curr.data);
            curr = curr.next;
            if (curr != null)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.printList();
        list.add(21);
        list.add(23);
        list.printList();
        list.delete(21);
        list.printList();

        LinkedList<String> fruitList = new LinkedList<>();
        fruitList.add("Orange");
        fruitList.add("Mango");
        fruitList.add("Apple");
        fruitList.printList();
        fruitList.delete("Apple");
        fruitList.printList();
    }
}
