package lab5;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
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
                for (int i = 0; i < data.length(); i++) {
                    System.out.print(data.charAt(i));
                }
                System.out.println();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
       Maze maze = new Maze("D:\\AGH\\2\\OOP\\src\\lab5\\maze_txt.txt");
       maze.loadMaze();
    }
}

