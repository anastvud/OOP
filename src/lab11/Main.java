package lab11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

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
            return;
        }

        if (!line.matches("[0-9a-zA-Z+\\-*/()=]+")) {
            throw new IllegalCharacterError(line + " illegal characters");
        }

        if (line.matches("^[^=]*$")) {
            throw new SyntaxError(line + " = expected");
        }


        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                try {
                    stack.pop();
                } catch (EmptyStackException e) {
                    throw new SyntaxError(line + " ) expected");
                }
            }
        }

        if (!stack.isEmpty()) {
            throw new SyntaxError(line + " ) expected");
        }

        if (line.contains("/0=")) {
            throw new ZeroDivisionError(line + " Zero Division Error");
        }

        if (line.matches(".*[a-zA-Z]+.*")) {
            throw new UndefinedVariableError(line + " Runtime error: Undefined variable");
        }

        //do the math
        String[] p = line.split("=");
        String expression = p[0].trim();
        double calculatedResult = evaluateExpression(expression);
        System.out.println(line + calculatedResult);

    }

    private static double evaluateExpression(String expression) throws IntegerLimitError {
        String[] tokens = expression.split("[+\\-*/]");
        char operator = expression.replaceAll("[0-9]", "").charAt(0);

        double operand1 = Double.parseDouble(tokens[0]);
        double operand2 = Double.parseDouble(tokens[1]);

        if (operand1 > Integer.MAX_VALUE || operand2 > Integer.MAX_VALUE) {
            throw new IntegerLimitError(expression + " integer limit (>32-bit required)");
        }

        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                return 0.0;
        }
    }
}
