package lab5;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a maze solver that loads a maze from a text file, finds a path from the start to the finish,
 * and creates an image of the cleared path.
 */
public class Maze implements Runnable {

    private final int WALL = 1;
    private final int CORRIDOR = 0;
    private final int PATH = 5;

    private int[][] maze;

    Point start;
    Point end;

    /**
     * Creates a new image based on the maze and saves it as a PNG file.
     */
    public void createNewImage() {
        int width = maze.length;
        int height = maze[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                int rgb = 0;
                switch (maze[i][j]) {
                    case PATH:
                       rgb = 3319890;
                       break;
                    case WALL:
                        rgb = 0;
                        break;
                    case CORRIDOR:
                        rgb = 6579300;
                        break;
                }
                image.setRGB(i, j, rgb);
            }
        }

        try {
            ImageIO.write(image, "PNG", new File("D:\\AGH\\2\\OOP\\src\\lab5\\cleared_image.png"));
        } catch (IOException e) {
            System.err.println("Error creating the image: " + e.getMessage());
        }
    }

    /**
     * Loads the maze from a text file and initializes the maze array.
     */
    public void loadMaze() {
        try {
            File myObj = new File("D:\\AGH\\2\\OOP\\src\\lab5\\maze_txt.txt");
            Scanner myReader = new Scanner(myObj);

            int numRows = 0;
            int numCols = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                numRows++;
                numCols = data.split("\t").length;
            }
            maze = new int[numRows][numCols];
            myReader = new Scanner(myObj);

            int row = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] cells = data.split("\t");
                for (int col = 0; col < cells.length; col++) {
                    char cell = cells[col].charAt(0);
                    int value = 0;
                    switch (cell) {
                        case 'W':
                            value = WALL;
                            break;
                        case 'C':
                            value = CORRIDOR;
                            break;
                        case 'S':
                            start = new Point(row, col);
                            break;
                        case 'F':
                            end = new Point(row, col);
                            break;
                        default:
                            continue;
                    }
                    maze[row][col] = value;
                }
                row++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading maze to array");
            e.printStackTrace();
        }
    }

    public Maze() {
        new Thread(this).start();
    }


    /**
     * Solves the maze using depth-first search.
     *
     * @param maze  The maze to solve.
     * @param start The starting point.
     * @param end   The ending point.
     * @return True if a path is found, false otherwise.
     */
    private boolean solveMaze(int[][] maze, Point start, Point end) {
        return dfs(maze, start.x, start.y, end.x, end.y);
    }

    /**
     * Depth-first search algorithm to find a path in the maze.
     *
     * @param maze      The maze to search.
     * @param currentX  Current x-coordinate.
     * @param currentY  Current y-coordinate.
     * @param endX      Target x-coordinate.
     * @param endY      Target y-coordinate.
     * @return True if a path is found, false otherwise.
     */
    private boolean dfs(int[][] maze, int currentX, int currentY, int endX, int endY) {
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
        maze[currentX][currentY] = WALL;

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

    /**
     * Prints the maze to the console.
     *
     * @param maze The maze to print.
     */
    private void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Runs the maze solver.
     */
    public void run() {
        loadMaze();
        if (solveMaze(maze, start, end)) {
            System.out.println("Path found!");
            printMaze(maze);
        } else {
            System.out.println("No path found");
        }
        createNewImage();
    }

    public static void main(String[] args) {
        Maze maze = new Maze();
    }
}
