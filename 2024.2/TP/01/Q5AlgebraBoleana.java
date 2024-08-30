import java.util.Scanner;
import java.util.Stack;

public class Q5AlgebraBoleana {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                boolean result = evaluateBooleanExpression(input);
                System.out.println(result ? "1" : "0");
            }
        }
        scanner.close();
    }

    private static boolean evaluateBooleanExpression(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            return false;
        }

        int n = Integer.parseInt(parts[0]);
        String expressionWithValues = parts[1];

        String[] expressionParts = expressionWithValues.split(" ", n + 1);
        if (expressionParts.length < n + 1) {
            return false;
        }

        String[] values = new String[n];
        System.arraycopy(expressionParts, 0, values, 0, n);
        String booleanExpression = expressionParts[n];

        for (int i = 0; i < n; i++) {
            booleanExpression = booleanExpression.replace("A", values[0])
                                                 .replace("B", values[1])
                                                 .replace("C", values.length > 2 ? values[2] : "0");
        }

        return evaluateExpression(booleanExpression);
    }

    private static boolean evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s", "");
        expression = expression.replace("and", "&");
        expression = expression.replace("or", "|");
        expression = expression.replace("not", "!");

        Stack<Boolean> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '0' || c == '1') {
                values.push(c == '1');
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.empty() && ops.peek() != '(') {
                    if (values.size() < 2) return false;
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                if (!ops.empty() && ops.peek() == '(') {
                    ops.pop();
                }
            } else if (c == '&' || c == '|') {
                while (!ops.empty() && hasPrecedence(c, ops.peek())) {
                    if (values.size() < 2) return false;
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(c);
            } else if (c == '!') {
                if (!values.empty()) {
                    values.push(!values.pop());
                }
            }
        }

        while (!ops.empty()) {
            if (values.size() < 2) return false;
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean applyOp(char op, boolean b, boolean a) {
        switch (op) {
            case '&':
                return a && b;
            case '|':
                return a || b;
        }
        return false;
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '&' || op1 == '|') && (op2 == '!' || op2 == '&')) {
            return false;
        }
        return true;
    }
}