package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Maze {

    private final String filePath;
    private ArrayList<ArrayList<Integer>> maze = new ArrayList<>();

    Maze(String file) {
        filePath = file;
    }

    public void loadMaze() {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<Integer> row = new ArrayList<>();
                for (int i = 0; i < data.length(); i++) {
                    char cell = data.charAt(i);
                    int value;
                    switch (cell) {
                        case 'W':
                            value = -1;
                            break;
                        case 'C':
                            value = 0;
                            break;
                        case 'S':
                            value = 1;
                            break;
                        case 'F':
                            value = -2;
                            break;
                        default:
                            continue;
                    }
                    row.add(value);
                }
                maze.add(row);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading maze to array");
            e.printStackTrace();
        }

        printMaze();
    }

    private void printMaze() {
        for (ArrayList<Integer> row : maze) {
            for (Integer cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
       Maze maze = new Maze("D:\\AGH\\2\\OOP\\src\\lab5\\maze_txt.txt");
       maze.loadMaze();
    }
}

