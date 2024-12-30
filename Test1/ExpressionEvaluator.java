public class ExpressionEvaluator {
    private final VariableStorage variableStorage;

    public ExpressionEvaluator(VariableStorage variableStorage) {
        this.variableStorage = variableStorage;
    }

    public int evaluateExpression(String expression) {
        // Handle expressions like "factorial(a)" or arithmetic
        if (expression.contains("factorial")) {
            return new Factorial(variableStorage).calculate(expression);  // Pass variableStorage
        }
        if (expression.contains("gcd")) {
            return new GCD().calculate(expression, variableStorage);  // Pass variableStorage
        }
        if (expression.contains("reverse")) {
            return new Reverse().calculate(expression);  // Reverse method doesn't need variableStorage
        }
        if (expression.contains("prime")) {
            return new PrimeCheck().calculate(expression, variableStorage);  // Pass variableStorage
        }

        // Regular arithmetic evaluation
        String[] tokens = expression.split("\\s+");
        int result = 0;
        int currentValue = 0;
        String operator = "+";

        for (String token : tokens) {
            if (isOperator(token)) {
                operator = token;
            } else {
                currentValue = getValue(token);
                result = applyOperator(result, currentValue, operator);
            }
        }
        return result;
    }

    public void handleFunctionCalls(String line) {
        // Process the function calls like "factorial", "gcd", etc.
        if (line.contains("factorial")) {
            new Factorial(variableStorage).execute(line);  // Pass variableStorage
        } else if (line.contains("gcd")) {
            new GCD().execute(line, variableStorage);  // Pass variableStorage
        } else if (line.contains("reverse")) {
            new Reverse().execute(line, variableStorage);  // Pass variableStorage
        } else if (line.contains("prime")) {
            new PrimeCheck().execute(line, variableStorage);  // Pass variableStorage
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int applyOperator(int left, int right, String operator) {
        return switch (operator) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    private int getValue(String str) {
        if (variableStorage.contains(str)) {
            return variableStorage.getVariable(str);
        }
        return Integer.parseInt(str);
    }
}
