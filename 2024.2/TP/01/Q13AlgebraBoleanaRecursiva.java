import java.util.Scanner;

public class Q13AlgebraBoleanaRecursiva {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        processarEntrada(scanner);
        scanner.close();
    }

    private static void processarEntrada(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            return;
        }
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            boolean result = evaluateBooleanExpression(input);
            System.out.println(result ? "1" : "0");
        }
        processarEntrada(scanner);
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
            booleanExpression = booleanExpression
                    .replace("A", values[0])
                    .replace("B", values[1])
                    .replace("C", values.length > 2 ? values[2] : "0");
        }

        return evaluateExpression(booleanExpression, 0, new Stack<>(), new Stack<>());
    }

    private static boolean evaluateExpression(String expression, int index, Stack<Boolean> values, Stack<Character> ops) {
        if (index == expression.length()) {
            while (!ops.empty()) {
                if (values.size() < 2) {
                    return false;
                }
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            }
            return values.pop();
        }

        char c = expression.charAt(index);
        if (c == '0' || c == '1') {
            values.push(c == '1');
        } else if (c == '(') {
            ops.push(c);
        } else if (c == ')') {
            while (!ops.empty() && ops.peek() != '(') {
                if (values.size() < 2) {
                    return false;
                }
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            }
            if (!ops.empty() && ops.peek() == '(') {
                ops.pop();
            }
        } else if (c == '&' || c == '|') {
            while (!ops.empty() && hasPrecedence(c, ops.peek())) {
                if (values.size() < 2) {
                    return false;
                }
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            }
            ops.push(c);
        } else if (c == '!') {
            if (!values.empty()) {
                values.push(!values.pop());
            }
        }
        return evaluateExpression(expression, index + 1, values, ops);
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
