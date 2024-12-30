public class Reverse {

    public int calculate(String expression) {
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        return reverseNumberWhileLoop(n);
    }

    public void execute(String line, VariableStorage variableStorage) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String expression = line.substring(line.indexOf('=') + 1).trim();
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr);
        int result = reverseNumberWhileLoop(n);
        variableStorage.storeVariable(varName, result);
    }

    private int reverseNumberWhileLoop(int n) {
        int reversed = 0;
        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed;
    }

    private int getVariableOrValue(String str) {
        if (VariableStorage.contains(str)) {
            return VariableStorage.getVariable(str);
        }
        return Integer.parseInt(str);
    }
}
