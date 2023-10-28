public class SinglyLinkedList {
    Node head;
    Node tail;
    int size;

    SinglyLinkedList() {
    }

    static class Node {
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

    void addInRow(int data, int row, int column, SinglyLinkedList columns) {
        Node node = new Node(data, row, column);
        Node current = this.head;
        Node pre = null;
        while (current != null && current.column < column) {
            pre = current;
            current = current.next;
        }
        if (current == this.head) {
            node.next = current;
            this.head = node;
            if (this.size == 0) this.tail = this.head;
        } else {
            pre.next = node;
            node.next = current;
        }
        columns.addInColumn(data,row,column);
        this.size++;
    }

    private void addInColumn(int data, int row, int column) {
        Node node = new Node(data, row, column);
        Node current = this.head;
        Node pre = null;
        while (current != null && current.row < row) {
            pre = current;
            current = current.next;
        }
        if (current == this.head) {
            node.next = current;
            this.head = node;
            if (this.size == 0) this.tail = this.head;
        } else {
            pre.next = node;
            node.next = current;
        }
        this.size++;
    }

    void remove(int column, SinglyLinkedList columns) {
        Node pre = null;
        Node node = this.head;
        while (node != null) {
            if (node.column == column) {                      //if it isn't 0,changed the data to 0!
                node.data = 0;
                columns.removeFromColumn(node.row);
                if (node == this.head) {                     //change the pointers
                    this.head = node.next;
                } else pre.next = node.next;
                break;
            } else if (node.column > column) break;                 //It's 0 by default!
            else pre = node;
            node = node.next;
        }
    }

    private void removeFromColumn(int row) {
        Node pre = null;
        Node node = this.head;
        while (node != null) {
            if (node.row == row) {                      //if it isn't 0,changed the data to 0!
                node.data = 0;
                if (node == this.head) {                     //change the pointers
                    this.head = node.next;
                } else pre.next = node.next;
                break;
            } else if (node.row > row) break;                 //It's 0 by default!
            else pre = node;
            node = node.next;
        }
    }


    void update(int newValue, int row, int column, SinglyLinkedList columns) {
        Node node = this.head;
        while (node != null) {
            if (node.column == column) {                      //if it isn't 0,changed the data!
                node.data = newValue;
                columns.updateColumn(newValue,row);
                break;
            } else if (node.column > column) {          //if it's 0,updating is like adding!
                addInRow(newValue, row, column, columns);
                break;
            } else if (newValue == 0) {
                remove(column, columns);
                break;
            }
            node = node.next;

        }
    }

    private void updateColumn(int newValue,int row) {
        Node node = this.head;
        while (node != null) {
            if (node.row == row) {
                node.data = newValue;
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
                result = "Row: " + node.row + " Column: " + node.column;
                break;
            }
            node = node.next;
        }
        return result;
    }
}