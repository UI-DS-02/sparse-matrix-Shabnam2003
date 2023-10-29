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
        Node nextColumn;
        Node nextRow;


        Node(int data, int row, int column) {
            this.data = data;
            this.row = row;
            this.column = column;
        }
    }

    String traverseInRow() {
        Node node = this.head;
        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(node.row).append(" ").append(node.column).append(" ").append(node.data).append("\n");
            node = node.nextColumn;
        }
        return result.toString();
    }

    String traverseInColumn() {
        Node node = this.head;
        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(node.row).append(" ").append(node.column).append(" ").append(node.data).append("\n");
            node = node.nextRow;
        }
        return result.toString();
    }

    void addInRow(int data, int row, int column, SinglyLinkedList columns) {
        Node node = new Node(data, row, column);
        Node current = this.head;
        Node pre = null;
        while (current != null && current.column < column) {            //find the place
            pre = current;
            current = current.nextColumn;
        }
        if (current == this.head) {
            node.nextColumn = current;
            this.head = node;
            if (this.size == 0) this.tail = this.head;
        } else {
            pre.nextColumn = node;
            node.nextColumn = current;
        }
        columns.addInColumn(node);
        this.size++;
    }

    private void addInColumn(Node node) {
        Node current = this.head;
        Node pre = null;
        while (current != null && current.row < node.row) {              //find the place
            pre = current;
            current = current.nextRow;
        }
        if (current == this.head) {
            node.nextRow = current;
            this.head = node;
            if (this.size == 0) this.tail = this.head;
        } else {
            pre.nextRow = node;
            node.nextRow = current;
        }
        this.size++;
    }

    void remove(int column, SinglyLinkedList columns) {
        Node pre = null;
        Node node = this.head;
        while (node != null) {
            if (node.column == column) {                      //if it isn't 0,changed the data to 0!
                node.data = 0;
                this.size--;
                columns.removeFromColumn(node.row);
                if (node == this.head) {                     //change the pointers
                    this.head = node.nextColumn;
                } else if (pre != null) {
                    pre.nextColumn = node.nextColumn;
                }
                break;
            } else if (node.column > column) break;                 //It's 0 by default!
            else pre = node;
            node = node.nextColumn;
        }
    }

    private void removeFromColumn(int row) {
        Node pre = null;
        Node node = this.head;
        while (node != null) {
            if (node.row == row) {                      //if it isn't 0,changed the data to 0!
                this.size--;
                if (node == this.head) {                     //change the pointers
                    this.head = node.nextRow;
                } else pre.nextRow = node.nextRow;
                break;
            } else if (node.row > row) break;                 //It's 0 by default!
            else pre = node;
            node = node.nextRow;
        }
    }


    void update(int newValue, int row, int column, SinglyLinkedList columns) {
        Node node = this.head;
        while (node != null) {
            if (node.column == column) {                      //if it isn't 0,changed the data!
                node.data = newValue;
                break;
            } else if (node.column > column) {          //if it's 0,updating is like adding!
                addInRow(newValue, row, column, columns);
                break;
            } else if (newValue == 0) {
                remove(column, columns);
                break;
            }
            node = node.nextColumn;

        }
    }


    //search in row
    String search(int value) {
        Node rowNode = this.head;
        String result = null;
        Node columnNode = this.head;
        while (columnNode != null) {
            if (columnNode.data == value) {                //search in a row
                result = "Row: " + columnNode.row + " Column: " + columnNode.column;
                break;
            } else if (rowNode!=null && rowNode.data == value) {            //search in a column
                result = "Row: " + rowNode.row + " Column: " + rowNode.column;
                break;
            }
            rowNode = columnNode.nextRow;
            columnNode = columnNode.nextColumn;
        }
        return result;
    }
}