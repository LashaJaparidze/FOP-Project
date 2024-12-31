public class Interpreter {
    private final VariableStorage variableStorage = new VariableStorage(); // Variable storage
    private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(variableStorage); // Expression evaluator

    public void eval(String code) {
        String[] lines = code.split(";\\n?"); // Split by semicolon and optional newline
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Handle variable declarations and assignments
            if (line.startsWith("val") || line.startsWith("var")) {
                handleAssignment(line);
            }
            // Handle println statements
            else if (line.startsWith("println")) {
                handlePrint(line);
            }
            // Handle function calls (factorial, gcd, reverse, prime)
            else {
                expressionEvaluator.handleFunctionCalls(line);
            }
        }
    }

    private void handleAssignment(String line) {
        String[] parts = line.split("=", 2);
        String declaration = parts[0].trim();
        String expression = parts[1].trim();

        // Extract variable name
        String varName = declaration.replaceFirst("val|var", "").trim();

        // Evaluate the expression and store the result
        int value = expressionEvaluator.evaluateExpression(expression);
        variableStorage.storeVariable(varName, value);
    }

    private void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        Integer value = variableStorage.getVariable(varName);
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Error: Variable " + varName + " not found.");
        }
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();

        // Combined program to test factorial calculation, sum, and printing
        String program = """
            val a = 10;
            val b = 20;
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

        interpreter.eval(program);
    }
}
