package lab5;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Creates a random maze, then solves it by finding a path from the
 * upper left corner to the lower right corner. (After doing
 * one maze, it waits a while then starts over by creating a
 * new random maze.)
 */
public class Maze implements Runnable {

    // a main routine makes it possible to run this class as a program
    public static void main(String[] args) {
        Maze maze = new Maze();
    }


    int[][] maze; // Description of state of maze. The value of maze[i][j]
    // is one of the constants wallCode, pathcode, emptyCode,
    // or visitedCode. (Value can also be negative, temporarily,
    // inside createMaze().)
    // A maze is made up of walls and corridors. maze[i][j]
    // is either part of a wall or part of a corridor. A cell
    // cell that is part of a corridor is represented by pathCode
    // if it is part of the current path through the maze, by
    // visitedCode if it has already been explored without finding
    // a solution, and by emptyCode if it has not yet been explored.

    final static int backgroundCode = 0;
    final static int wallCode = -1;
    final static int pathCode = 2;
    final static int emptyCode = 3;
    final static int visitedCode = 4;

    Color[] color; // colors associated with the preceding 5 constants;
    int rows = 41; // number of rows of cells in maze, including a wall around edges
    int columns = 41; // number of columns of cells in maze, including a wall around edges
    int border = 0; // minimum number of pixels between maze and edge of panel
    int sleepTime = 5000; // wait time after solving one maze before making another
    int speedSleep = 30; // short delay between steps in making and solving maze
    int blockSize = 12; // size of each cell

    int width = -1; // width of panel, to be set by checkSize()
    int height = -1; // height of panel, to be set by checkSize()

    int totalWidth; // width of panel, minus border area (set in checkSize())
    int totalHeight; // height of panel, minus border area (set in checkSize())
    int left; // left edge of maze, allowing for border (set in checkSize())
    int top; // top edge of maze, allowing for border (set in checkSize())

    boolean mazeExists = false; // set to true when maze[][] is valid; used in
    // redrawMaze(); set to true in createMaze(), and
    // reset to false in run()

    public void loadMaze() {
        try {
            File myObj = new File("D:\\AGH\\2\\OOP\\src\\lab5\\maze_txt.txt");
            Scanner myReader = new Scanner(myObj);

            int numRows = 0;
            int numCols = 0;

            // Count the number of rows and columns in the maze
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                numRows++;
                numCols = data.split("\t").length;
            }

            // Initialize the maze array based on the counted rows and columns
            maze = new int[numRows][numCols];

            // Reset the scanner to read from the beginning of the file
            myReader = new Scanner(myObj);

            int row = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] cells = data.split("\t");
                for (int col = 0; col < cells.length; col++) {
                    char cell = cells[col].charAt(0); // Get the first character after splitting
                    int value;
                    switch (cell) {
                        case 'W':
                            value = wallCode;
                            break;
                        case 'C':
                            value = backgroundCode;
                            break;
                        case 'S':
                            value = 0;
                            break;
                        case 'F':
                            value = 0;
                            break;
                        default:
                            continue;
                    }
                    maze[row][col] = value;
                }
                row++;
            }
            System.out.println(maze.length + "  " + maze[0].length);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading maze to array");
            e.printStackTrace();
        }

        printMaze();
    }

    private void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    public Maze() {
        new Thread(this).start();
    }


    public void run() {
        // run method for thread repeatedly makes a maze and then solves it
//        try {
//            Thread.sleep(1000);
//        } // wait a bit before starting
//        catch (InterruptedException e) {
//        }
//        while (true) {
//            makeMaze();
            loadMaze();
            System.out.println(solveMaze(1,0));
            System.out.println();
            System.out.println();
            System.out.println();
            printMaze();
//            synchronized (this) {
//                try {
//                    wait(sleepTime);
//                } catch (InterruptedException e) {
//                }
//            }
//            mazeExists = false;
//            repaint();
//        }
    }

    boolean solveMaze(int row, int col) {
        // Try to solve the maze by continuing current path from position
        // (row,col). Return true if a solution is found. The maze is
        // considered to be solved if the path reaches the lower right cell.
        if (maze[row][col] == emptyCode) {
            maze[row][col] = pathCode; // add this cell to the path
//            repaint();
            if (row == rows - 2 && col == columns - 1)
                return true; // path has reached goal
            try {
                Thread.sleep(speedSleep);
            } catch (InterruptedException e) {
            }
            if (solveMaze(row - 1, col) || // try to solve maze by extending path
                    solveMaze(row, col - 1) || // in each possible direction
                    solveMaze(row + 1, col) ||
                    solveMaze(row, col + 1))
                return true;
            // maze can't be solved from this cell, so backtrack out of the cell
            maze[row][col] = visitedCode; // mark cell as having been visited
//            repaint();
            synchronized (this) {
                try {
                    wait(speedSleep);
                } catch (InterruptedException e) {
                }
            }
        }
        return false;
    }
}
