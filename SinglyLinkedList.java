public class SinglyLinkedList {
    Node head;
    Node tail;
    int size;

    SinglyLinkedList() {
    }

    class Node {
        int data;
        int raw;
        int column;
        Node next;

        Node(int data,int raw,int column) {
            this.data = data;
            this.raw=raw;
            this.column=column;
        }
    }

    String traverse() {
        Node node = this.head;
        StringBuilder result= new StringBuilder();
        while (node != null) {
            result.append(node.raw).append(" ").append(node.column).append(" ").append(node.data).append("\n");
            node = node.next;
        }
        return result.toString();
    }

    void add(int data,int raw,int column) {
        Node node = new Node(data,raw,column);
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

//    void remove(int row,int column){}
//    void update(int newValue,int row,int column){}
//    String search(int value){return "";}
}