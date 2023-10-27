import java.io.*;
import java.util.Scanner;

public class SparseMatrix {

    static String showCompletely(SinglyLinkedList[] matrix, int columns, int raw) {
        StringBuilder result=new StringBuilder();
        for (int i = 0; i < raw; i++) {
            //A string for each raw and value is 0 for all columns
            StringBuilder eachRaw = new StringBuilder();
            eachRaw.append("0 ".repeat(Math.max(0, columns)));

            SinglyLinkedList.Node node = matrix[i].head;
            if (node != null) {
                int numberOfPre=0;
                for (int j = 0; j < columns; j++) {
                    if (node.column == j) {
                        //replace the real value
                        eachRaw.replace(j*2+numberOfPre,j*2+numberOfPre+1, String.valueOf(node.data));
                        //if we have the previous node,every index will add
                        numberOfPre+=String.valueOf(node.data).length()-1;
                        node = node.next;
                    }
                    if (node==null)break;
                }
            }
            result.append(eachRaw).append("\n");
        }
        return result.toString();
    }


    static void fullCompact(BufferedReader bufferedReader, int numberOfColumns, SinglyLinkedList[] matrix) throws IOException {
        int row = -1;
        String raws = bufferedReader.readLine();
        String[] input;
        while (raws != null) {
            input = raws.split(",");
            row++;
            for (int i = 0; i < numberOfColumns; i++) {

                //if it wasn't 0, we add it.

                if (!input[i].equals("0")) {
                    matrix[row].add(Integer.parseInt(input[i]), row, i);
                }
            }
            raws = bufferedReader.readLine();
        }
    }


    public static void main(String[] args) throws IOException {

        //read the csv file
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\M(10,5).csv"));

        //numberOfColumns
        String raws = bufferedReader.readLine();
        int numberOfColumns = raws.split(",").length;

        //numberOfRows

        int numberOfRows = 1;
        while (bufferedReader.readLine() != null) {
            numberOfRows++;
        }

        //read again for fulling

        bufferedReader = new BufferedReader(new FileReader("D:\\data structures\\practice\\miniProject1\\sparse-matrix-Shabnam2003\\M(10,5).csv"));
        SinglyLinkedList[] matrix = new SinglyLinkedList[numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            matrix[i] = new SinglyLinkedList();
        }
        fullCompact(bufferedReader, numberOfColumns, matrix);


        String input;
        do {
            System.out.println("Please choose one of following orders:\n1.add\n2.delete\n3.update\n4.search\n5.show matrix\n6.show compact form\n0.exit");
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.println("Please send me data,raw,and column:");
                    String[] orders = sc.nextLine().split("\\s");
                    matrix[Integer.parseInt(orders[1])].add(Integer.parseInt(orders[0]), Integer.parseInt(orders[1]), Integer.parseInt(orders[2]));
                    System.out.println("Done :)");
                }
                case "2" -> {
                    System.out.println("Please send me raw,and column:");
                    String[] orders = sc.nextLine().split("\\s");
//                    matrix[Integer.parseInt(orders[0])].remove(Integer.parseInt(orders[0]), Integer.parseInt(orders[1]));
                    System.out.println("Done :)");
                }
                case "3" -> {
                    System.out.println("Please send me new data,raw,and column:");
                    String[] orders = sc.nextLine().split("\\s");
//                    matrix[Integer.parseInt(orders[1])].update(Integer.parseInt(orders[0]), Integer.parseInt(orders[1]), Integer.parseInt(orders[2]));
                    System.out.println("Done :)");
                }
                case "4" -> {
                    System.out.println("Please send me data:");
                    String orders = sc.nextLine();
                    for (int i = 0; i < numberOfRows; i++) {
//                        System.out.println(matrix[i].search(Integer.parseInt(orders)));
                    }
                }
                case "5" -> System.out.println(showCompletely(matrix, numberOfColumns, numberOfRows));
                case "6" -> {
                    for (int i = 0; i < numberOfRows; i++) {
                        String result = matrix[i].traverse();
                        if (result != null) System.out.print(result);
                    }
                }
                default -> input = "0";
            }
        } while (!input.equals("0"));


    }
}
