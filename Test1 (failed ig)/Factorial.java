
public class Factorial {

    private final VariableStorage variableStorage;

    public Factorial(VariableStorage variableStorage) {
        this.variableStorage = variableStorage;
    }

    public int calculate(String expression) {
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        return (int) factorial(n);
    }

    public void execute(String line) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String expression = line.substring(line.indexOf('=') + 1).trim();
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        long result = factorial(n);
        variableStorage.storeVariable(varName, (int) result);
    }

    private long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private int getVariableOrValue(String str) {
        if (variableStorage.contains(str)) {
            return variableStorage.getVariable(str);
        }
        return Integer.parseInt(str);
    }
}
