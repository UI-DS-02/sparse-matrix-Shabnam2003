public class SinglyLinkedList {
    Node head;
    Node tail;
    int size;

    SinglyLinkedList() {
    }

    class Node {
        int data;
        int row;
        int column;
        Node next;

        Node(int data, int row, int column) {
            this.data = data;
            this.row = row;
            this.column = column;
        }
    }

    String traverse() {
        Node node = this.head;
        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(node.row).append(" ").append(node.column).append(" ").append(node.data).append("\n");
            node = node.next;
        }
        return result.toString();
    }

    void add(int data, int row, int column) {
        Node node = new Node(data, row, column);
        Node current = this.head;
        Node pre = null;
        while (current != null && current.column < column) {
            pre = current;
            current = current.next;
        }
        if (current == this.head) {
            node.next = this.head;
            this.head = node;
            if (this.size == 0) this.tail = this.head;
        } else {
            pre.next = node;
            node.next = current;
        }
        this.size++;
    }

    void remove(int column) {
        Node node = this.head;
        while (node != null) {
            if (node.column == column) {                      //if it isn't 0,changed the data to 0!
                node.data = 0;
                break;
            } else if (node.column > column) break;                 //It's 0 by default!
            node = node.next;
        }
    }


    void update(int newValue, int row, int column) {
        Node node = this.head;
        while (node != null) {
            if (node.column == column) {                      //if it isn't 0,changed the data!
                node.data = newValue;
                break;
            } else if (node.column > column) {          //if it's 0,updating is like adding!
                add(newValue, row, column);
                break;
            }
            node = node.next;

        }
    }

    //search in row
    String search(int value) {
        String result = null;
        Node node = this.head;
        while (node != null) {
            if (node.data == value) {
                result = "Row: " + node.row + "Column: " + node.column;
                break;
            }
            node = node.next;
        }
        return result;
    }
}