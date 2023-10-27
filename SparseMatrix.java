import java.io.*;
import java.util.Scanner;

public class SparseMatrix {

    static void saveCompact(SinglyLinkedList[] matrix, int numberOfRows) throws IOException {
        FileWriter writer = new FileWriter("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\compact.csv");
        for (int i = 0; i < numberOfRows; i++) {
            String element = matrix[i].traverse();
            if (element != null) writer.write(matrix[i].traverse());
        }
        writer.close();
    }

    static void saveMatrix(SinglyLinkedList[] matrix, int columns, int rows) throws IOException {
        FileWriter writer = new FileWriter("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\normal.csv");
        writer.write(showCompletely(matrix, columns, rows));
        writer.close();
    }

    static String showCompletely(SinglyLinkedList[] matrix, int columns, int rows) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            //A string for each raw and value is 0 for all columns
            StringBuilder eachRaw = new StringBuilder();
            eachRaw.append(" 0  ".repeat(Math.max(0, columns)));

            SinglyLinkedList.Node node = matrix[i].head;
            if (node != null) {
                for (int j = 0; j < columns; j++) {
                    if (node.column == j) {
                        //replace the real value
                        eachRaw.replace(j * 4+1, j * 4 + String.valueOf(node.data).length()+1, String.valueOf(node.data));

                        node = node.next;
                    }
                    if (node == null) break;
                }
            }
            result.append(eachRaw).append("\n\n");
        }
        return result.toString();
    }


    static void fullCompact(BufferedReader bufferedReader, int numberOfColumns, SinglyLinkedList[] matrix) throws IOException {
        int row = -1;
        String rows = bufferedReader.readLine();
        String[] input;
        while (rows != null) {
            input = rows.split(",");
            row++;
            for (int i = 0; i < numberOfColumns; i++) {

                //if it wasn't 0, we add it.

                if (!input[i].equals("0")) {
                    matrix[row].add(Integer.parseInt(input[i]), row, i);
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

        bufferedReader = new BufferedReader(new FileReader(address));
        SinglyLinkedList[] matrix = new SinglyLinkedList[numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            matrix[i] = new SinglyLinkedList();
        }
        fullCompact(bufferedReader, numberOfColumns, matrix);
        //save matrix in csv
        saveCompact(matrix, numberOfRows);

        //save matrix in csv
        saveMatrix(matrix, numberOfColumns, numberOfRows);


        String input;
        do {
            System.out.println("Please choose one of following orders:\n1.add\n2.delete\n3.update\n4.search\n5.show matrix\n6.show compact form\n0.exit");
            input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.println("Please send me data,row,and column:");
                    String[] orders = sc.nextLine().split("\\s");
                    matrix[Integer.parseInt(orders[1])].add(Integer.parseInt(orders[0]), Integer.parseInt(orders[1]), Integer.parseInt(orders[2]));

                    //save matrix in csv
                    saveCompact(matrix, numberOfRows);
                    saveMatrix(matrix, numberOfColumns, numberOfRows);


                    System.out.println("Adding done :)");
                }
                case "2" -> {
                    System.out.println("Please send me row,and column:");
                    String[] orders = sc.nextLine().split("\\s");
                    matrix[Integer.parseInt(orders[0])].remove(Integer.parseInt(orders[1]));

                    //save matrix in csv
                    saveCompact(matrix, numberOfRows);
                    saveMatrix(matrix, numberOfColumns, numberOfRows);

                    System.out.println("Removing done :)");
                }
                case "3" -> {
                    System.out.println("Please send me new data,row,and column:");
                    String[] orders = sc.nextLine().split("\\s");
                    matrix[Integer.parseInt(orders[1])].update(Integer.parseInt(orders[0]), Integer.parseInt(orders[1]), Integer.parseInt(orders[2]));

                    //save matrix in csv
                    saveCompact(matrix, numberOfRows);
                    saveMatrix(matrix, numberOfColumns, numberOfRows);

                    System.out.println("Updating done :)");
                }
                case "4" -> {
                    System.out.println("Please send me data:");
                    String orders = sc.nextLine();
                    String resultUpdate = null;
                    //search in each raw
                    for (int i = 0; i < numberOfRows; i++) {
                        resultUpdate = matrix[i].search(Integer.parseInt(orders));
                        if (resultUpdate != null) {
                            System.out.println(resultUpdate);

                            //save matrix in csv
                            saveCompact(matrix, numberOfRows);
                            saveMatrix(matrix, numberOfColumns, numberOfRows);

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
