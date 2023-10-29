import java.io.*;
import java.util.Scanner;

public class SparseMatrix {

    static void saveCompactFromRow(SinglyLinkedList[] rowMatrix, int numberOfRows) throws IOException {
        FileWriter writer = new FileWriter("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\compact.csv");
        for (int i = 0; i < numberOfRows; i++) {
            String element = rowMatrix[i].traverseInRow();
            if (element != null) writer.write(rowMatrix[i].traverseInRow());
        }
        writer.close();
    }


    static void saveCompactFromColumn(SinglyLinkedList[] columnMatrix, int numberOfColumns) throws IOException {
        FileWriter writer = new FileWriter("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\compact from columns.csv");
        for (int i = 0; i < numberOfColumns; i++) {
            String element = columnMatrix[i].traverseInColumn();
            if (element != null) writer.write(columnMatrix[i].traverseInColumn());
        }
        writer.close();
    }

    static void saveMatrixOfRow(SinglyLinkedList[] matrix, int columns, int rows) throws IOException {
        FileWriter writer = new FileWriter("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\normal.csv");
        writer.write(showCompletely(matrix, columns, rows));
        writer.close();
    }



    static String showCompletely(SinglyLinkedList[] rowMatrix, int columns, int rows) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            //A string for each raw and value is 0 for all columns
            StringBuilder eachRaw = new StringBuilder();
            eachRaw.append(" 0  ".repeat(Math.max(0, columns)));

            SinglyLinkedList.Node node = rowMatrix[i].head;
            if (node != null) {
                for (int j = 0; j < columns; j++) {
                    if (node.column == j) {
                        //replace the real value
                        eachRaw.replace(j * 4 + 1, j * 4 + String.valueOf(node.data).length() + 1, String.valueOf(node.data));

                        node = node.nextColumn;
                    }
                    if (node == null) break;
                }
            }
            result.append(eachRaw).append("\n\n");
        }
        return result.toString();
    }


    static void fill(BufferedReader bufferedReader, int numberOfColumns, SinglyLinkedList[] rowMatrix, SinglyLinkedList[] columnMatrix) throws IOException {
        int row = -1;
        String rows = bufferedReader.readLine();
        String[] input;
        while (rows != null) {
            input = rows.split(",");
            row++;
            for (int i = 0; i < numberOfColumns; i++) {
                //if it wasn't 0, we add it.
                if (!input[i].equals("0")) {
                    rowMatrix[row].addInRow(Integer.parseInt(input[i]), row, i, columnMatrix[i]);      //add to row and column
                }
            }
            rows = bufferedReader.readLine();
        }
    }


    public static void main(String[] args) throws IOException {

        //input the file address
        System.out.println("Please enter the file address:");
        Scanner sc = new Scanner(System.in);
        String address = sc.nextLine();

        //read the csv file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(address));
        //numberOfColumns
        String raws = bufferedReader.readLine();
        int numberOfColumns = raws.split(",").length;

        //numberOfRows

        int numberOfRows = 1;
        while (bufferedReader.readLine() != null) {
            numberOfRows++;
        }

        //read again for fulling

        SinglyLinkedList[] rowMatrix = new SinglyLinkedList[numberOfRows];
        SinglyLinkedList[] columnMatrix = new SinglyLinkedList[numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            rowMatrix[i] = new SinglyLinkedList();
        }

        for (int i = 0; i < numberOfColumns; i++) {
            columnMatrix[i] = new SinglyLinkedList();
        }

        bufferedReader = new BufferedReader(new FileReader(address));
        fill(bufferedReader, numberOfColumns, rowMatrix, columnMatrix);

        //save rowMatrix in csv
        saveCompactFromRow(rowMatrix, numberOfRows);
        saveCompactFromColumn(columnMatrix,numberOfColumns);

        //save rowMatrix in csv
        saveMatrixOfRow(rowMatrix, numberOfColumns, numberOfRows);


        String input;
        do {
            System.out.println("Please choose one of following orders:\n1.add\n2.delete\n3.update\n4.search\n5.show matrix\n6.show compact form\n0.exit");
            input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.println("Please send me data,row,and column:");
                    String[] orders = sc.nextLine().split("\\s");

                    rowMatrix[Integer.parseInt(orders[1])].addInRow(Integer.parseInt(orders[0]), Integer.parseInt(orders[1]), Integer.parseInt(orders[2]),columnMatrix[Integer.parseInt(orders[2])]);

                    //save rowMatrix in csv
                    saveCompactFromRow(rowMatrix, numberOfRows);
                    saveCompactFromColumn(columnMatrix,numberOfColumns);
                    saveMatrixOfRow(rowMatrix, numberOfColumns, numberOfRows);


                    System.out.println("Adding done :)");
                }
                case "2" -> {
                    System.out.println("Please send me row,and column:");
                    String[] orders = sc.nextLine().split("\\s");
                    rowMatrix[Integer.parseInt(orders[0])].remove(Integer.parseInt(orders[1]),columnMatrix[Integer.parseInt(orders[1])]);

                    //save rowMatrix in csv
                    saveCompactFromRow(rowMatrix, numberOfRows);
                    saveCompactFromColumn(columnMatrix,numberOfColumns);
                    saveMatrixOfRow(rowMatrix, numberOfColumns, numberOfRows);

                    System.out.println("Removing done :)");
                }
                case "3" -> {
                    System.out.println("Please send me new data,row,and column:");
                    String[] orders = sc.nextLine().split("\\s");
                    rowMatrix[Integer.parseInt(orders[1])].update(Integer.parseInt(orders[0]), Integer.parseInt(orders[1]), Integer.parseInt(orders[2]),columnMatrix[ Integer.parseInt(orders[2])]);

                    //save rowMatrix in csv
                    saveCompactFromRow(rowMatrix, numberOfRows);
                    saveCompactFromColumn(columnMatrix,numberOfColumns);
                    saveMatrixOfRow(rowMatrix, numberOfColumns, numberOfRows);

                    System.out.println("Updating done :)");
                }
                case "4" -> {
                    System.out.println("Please send me the data:");
                    String orders = sc.nextLine();
                    String resultUpdate = null;
                    //search in each raw
                    for (int i = 0; i < numberOfRows; i++) {
                        resultUpdate = rowMatrix[i].search(Integer.parseInt(orders));
                        if (resultUpdate != null) {
                            System.out.println(resultUpdate);
                            break;
                        }
                    }
                    if (resultUpdate == null) System.out.println("It doesn't exist!");
                }
                case "5" -> {
                    bufferedReader = new BufferedReader(new FileReader("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\normal.csv"));
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        System.out.println(line);
                        line = bufferedReader.readLine();
                    }
                }
                case "6" -> {
                    bufferedReader = new BufferedReader(new FileReader("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\compact.csv"));
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        System.out.println(line);
                        line = bufferedReader.readLine();
                    }
                }
                default -> input = "0";
            }
        } while (!input.equals("0"));


    }
}
