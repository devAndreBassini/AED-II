import java.util.Scanner;

public class Q13AlgebraBoleana {

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

        return evaluateExpression(replaceValues(booleanExpression, values));
    }

    private static String replaceValues(String expression, String[] values) {
        return replaceValues(expression, values, 0);
    }

    private static String replaceValues(String expression, String[] values, int index) {
        if (index >= values.length) {
            return expression;
        }
        String var = Character.toString((char)('A' + index));
        expression = expression.replace(var, values[index]);
        return replaceValues(expression, values, index + 1);
    }

    private static boolean evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s", "");
        expression = expression.replace("and", "&");
        expression = expression.replace("or", "|");
        expression = expression.replace("not", "!");

        return evaluate(expression);
    }

    private static boolean evaluate(String expression) {
        if (expression.isEmpty()) return false;

        int index = findOperatorIndex(expression);
        if (index == -1) {
            return expression.charAt(0) == '1';
        }

        char op = expression.charAt(index);
        String left = expression.substring(0, index);
        String right = expression.substring(index + 1);

        if (op == '&') {
            return evaluate(left) && evaluate(right);
        } else if (op == '|') {
            return evaluate(left) || evaluate(right);
        } else if (op == '!') {
            return !evaluate(right);
        }
        return false;
    }

    private static int findOperatorIndex(String expression) {
        return findOperatorIndex(expression, 0);
    }

    private static int findOperatorIndex(String expression, int start) {
        int level = 0;
        for (int i = start; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                level++;
            } else if (c == ')') {
                level--;
            } else if ((c == '&' || c == '|') && level == 0) {
                return i;
            } else if (c == '!') {
                return i;
            }
        }
        return -1;
    }
}
