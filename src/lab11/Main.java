package lab11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class SyntaxError extends Exception {
    public SyntaxError(String message) {
        super(message);
    }
}

class ZeroDivisionError extends Exception {
    public ZeroDivisionError(String message) {
        super(message);
    }
}

class UndefinedVariableError extends Exception {
    public UndefinedVariableError(String message) {
        super(message);
    }
}

class IntegerLimitError extends Exception {
    public IntegerLimitError(String message) {
        super(message);
    }
}

class IllegalCharacterError extends Exception {
    public IllegalCharacterError(String message) {
        super(message);
    }
}

public class Main {

    public static void main(String[] args) {
        String filename = "D:\\AGH\\2\\OOP\\src\\lab11\\text.txt"; // Replace with the actual path to your file

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    analyzeLine(line);
                } catch (SyntaxError | ZeroDivisionError | UndefinedVariableError | IntegerLimitError | IllegalCharacterError e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to open or read the file.");
        }
    }

    private static void analyzeLine(String line) throws SyntaxError, ZeroDivisionError, UndefinedVariableError, IntegerLimitError, IllegalCharacterError {
        line = line.trim();
        if (line.isEmpty()) {
            return; // Empty line
        }

//        String[] p = line.split("=");
//
//        if (p.length != 2) {
//            throw new SyntaxError("= expected");
//        }

        // Check for illegal characters
        if (!line.matches("[0-9a-zA-Z+\\-*/()=]+")) {
            throw new IllegalCharacterError(line + "Illegal characters");
        }

        // Check for syntax errors
        if (!line.matches("^[0-9+\\-*/()=]+$")) {
            throw new SyntaxError("Syntax error in the line: " + line);
        }

        // Check for zero division
        if (line.contains("/0=")) {
            throw new ZeroDivisionError("Zero Division Error in the line: " + line);
        }

        // Check for undefined variable
        if (line.matches(".*[a-zA-Z]+.*")) {
            throw new UndefinedVariableError("Runtime error: Undefined variable in the line: " + line);
        }

        // Check for integer limit (>32-bit required)
        if (line.matches(".*[0-9]+/[0-9]+=[0-9]+.*")) {
            String[] parts = line.split("/");
            long numerator = Long.parseLong(parts[0]);
            long denominator = Long.parseLong(parts[1].split("=")[0]);
            if (numerator > Integer.MAX_VALUE || denominator > Integer.MAX_VALUE) {
                throw new IntegerLimitError("Integer limit (>32-bit required) in the line: " + line);
            }
        }


    }
}
