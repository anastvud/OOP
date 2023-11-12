package lab5;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze implements Runnable {

    // a main routine makes it possible to run this class as a program
    public static void main(String[] args) {
        Maze maze = new Maze();
    }


    int[][] maze;

    final static int backgroundCode = 0;
    final static int wallCode = 100;

    private static final int WALL = 100;
    private static final int CORRIDOR = 0;
    private static final int PATH = 200;


    public void createNewImage() {
        int width = maze.length;
        int height = maze[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                int rgb = (maze[i][j] << 16) | (maze[i][j] << 8) | maze[i][j];
                image.setRGB(i, j, rgb);
            }
        }

        try {
            ImageIO.write(image, "PNG", new File("D:\\AGH\\2\\OOP\\src\\lab5\\cleared_image.png"));
        } catch (IOException e) {
            System.err.println("Error creating the image: " + e.getMessage());
        }
    }


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


    private static boolean solveMaze(int[][] maze, int startX, int startY, int endX, int endY) {
        return dfs(maze, startX, startY, endX, endY);
    }

    private static boolean dfs(int[][] maze, int currentX, int currentY, int endX, int endY) {
        int rows = maze.length;
        int columns = maze[0].length;

        // Check if the current position is out of bounds or a wall
        if (currentX < 0 || currentY < 0 || currentX >= rows || currentY >= columns || maze[currentX][currentY] == WALL) {
            return false;
        }

        // Check if we have reached the exit
        if (currentX == endX && currentY == endY) {
            maze[currentX][currentY] = PATH;
            return true;
        }

        // Mark the current position as visited
        maze[currentX][currentY] = WALL; // Marked as visited (assuming -1 represents visited)

        // Explore all possible moves: up, down, left, right
        if (
                dfs(maze, currentX - 1, currentY, endX, endY) ||
                        dfs(maze, currentX + 1, currentY, endX, endY) ||
                        dfs(maze, currentX, currentY - 1, endX, endY) ||
                        dfs(maze, currentX, currentY + 1, endX, endY)
        ) {
            // If a path is found, mark the current position as part of the path
            maze[currentX][currentY] = PATH;
            return true;
        }

        // Backtrack: unmark the current position
        maze[currentX][currentY] = CORRIDOR;

        return false;
    }

    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }


    public void run() {


            loadMaze();

        if (solveMaze(maze, 1, 0, maze.length - 2, maze[0].length - 1)) {
            System.out.println("Path found!");
            printMaze(maze);
        } else {
            System.out.println("No path found.");
        }
        createNewImage();

    }
}
