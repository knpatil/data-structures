package linkedlists;

public class LinkedList {
    Node head;

    public void add(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void delete(int data) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (curr.data == data) {
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
        Node curr = head;
        System.out.print("List contains: [");
        while (curr != null) {
            System.out.print(curr.data);
            curr = curr.next;
            if (curr != null)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.printList();
        list.add(10);
        list.add(11);
        list.add(12);
        list.printList();
        list.delete(11);
        list.printList();
    }
}
