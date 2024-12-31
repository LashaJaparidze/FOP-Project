import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private final Map<String, Integer> variables = new HashMap<>(); // Store the variables

    public void eval(String code) {
        String[] lines = code.split(";\\n?"); // Split with semicolon
        for (String line : lines) {
            line = line.trim(); // Clean line
            if (line.isEmpty()) continue;

            if (line.startsWith("val") || line.startsWith("var")) {
                handleAssignment(line); // Handle the assignment
            } else if (line.startsWith("println")) {
                handlePrint(line); // Print something
            } else if (line.contains("factorial")) {
                handleFactorial(line); // Handle factorial
            } else if (line.contains("gcd")) {
                handleGcdWhileLoop(line); // Handle GCD
            } else if (line.contains("reverse")) {
                handleReverseNumberWhileLoop(line); // Handle reverse
            } else if (line.contains("prime")) {
                handlePrimeCheck(line); // Prime check
            }
        }
    }

    private void handleAssignment(String line) {
        String[] parts = line.split("=", 2);
        String varName = parts[0].replaceFirst("val|var", "").trim();
        int value = evaluateExpression(parts[1].trim());
        variables.put(varName, value); // Store value in variable
    }

    private int evaluateExpression(String expression) {
        if (expression.contains("factorial")) return handleFactorialExpression(expression);
        if (expression.contains("gcd")) return handleGcdExpression(expression);
        if (expression.contains("reverse")) return handleReverseNumberExpression(expression);
        if (expression.contains("prime")) return handlePrimeExpression(expression);

        String[] tokens = expression.split("\\s+");
        int result = 0;
        int currentValue = 0;
        String operator = "+";

        for (String token : tokens) {
            if (isOperator(token)) {
                operator = token; // Set operator
            } else {
                if (variables.containsKey(token)) {
                    currentValue = variables.get(token); // Get value from variable
                } else {
                    currentValue = Integer.parseInt(token); // Convert to int
                }
                result = applyOperator(result, currentValue, operator); // Apply operator
            }
        }
        return result; // Return result
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
            default -> throw new IllegalArgumentException("Unsupported operator"); // Error if operator is unknown
        };
    }

    private void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        Integer value = variables.get(varName);
        if (value != null) {
            System.out.println(value); // Print value
        } else {
            System.out.println("Error: " + varName + " not found."); // Error if variable not found
        }
    }

    private void handleFactorial(String line) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String numberStr = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        long result = factorial(n);
        variables.put(varName, (int) result); // Store factorial result
    }

    private int handleFactorialExpression(String expression) {
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        return (int) factorial(n); // Return factorial result
    }

    private int handleGcdExpression(String expression) {
        String params = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        String[] numbers = params.split(",");
        int num1 = getVariableOrValue(numbers[0].trim());
        int num2 = getVariableOrValue(numbers[1].trim());
        return gcd(num1, num2); // Return GCD
    }

    private void handleGcdWhileLoop(String line) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String params = line.substring(line.indexOf('=') + 1).trim();
        String[] numbers = params.substring(params.indexOf('(') + 1, params.indexOf(')')).trim().split(",");
        int num1 = getVariableOrValue(numbers[0].trim());
        int num2 = getVariableOrValue(numbers[1].trim());
        int result = gcdWhileLoop(num1, num2);
        variables.put(varName, result); // Store GCD result
    }

    private void handleReverseNumberWhileLoop(String line) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String numberStr = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        int result = reverseNumberWhileLoop(n);
        variables.put(varName, result); // Store reversed number
    }

    private int handleReverseNumberExpression(String expression) {
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        return reverseNumberWhileLoop(n); // Return reversed number
    }

    private void handlePrimeCheck(String line) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String numberStr = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        boolean result = isPrime(n);
        variables.put(varName, result ? 1 : 0); // Store prime check result
    }

    private int handlePrimeExpression(String expression) {
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        return isPrime(n) ? 1 : 0; // Return 1 if prime, else 0
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true; // Return if prime
    }

    private int getVariableOrValue(String str) {
        if (variables.containsKey(str)) {
            return variables.get(str); // Return variable value
        }
        return Integer.parseInt(str); // Return integer value
    }

    private long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result; // Return factorial
    }

    private int gcdWhileLoop(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; // Return GCD result
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b); // Return GCD result
    }

    private int reverseNumberWhileLoop(int n) {
        int reversed = 0;
        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed; // Return reversed number
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();

        String program = """
            val a = 6;
            val b = 9;
            var sum = a + b;
            println(sum);
            sum = sum + 5;
            println(sum);
            val c = factorial(a);
            println(c);
            val d = gcd(a, b);
            println(d);
            val e = reverse(a);
            println(e);
            val f = prime(a);
            println(f);
        """;

        interpreter.eval(program); // Execute the program
    }
}
