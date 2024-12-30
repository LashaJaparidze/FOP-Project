public class PrimeCheck {

    public int calculate(String expression, VariableStorage variableStorage) {
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr, variableStorage); // Pass variableStorage here
        return isPrime(n) ? 1 : 0;
    }

    public void execute(String line, VariableStorage variableStorage) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String expression = line.substring(line.indexOf('=') + 1).trim();
        String numberStr = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        int n = getVariableOrValue(numberStr, variableStorage); // Pass variableStorage here
        boolean result = isPrime(n);
        variableStorage.storeVariable(varName, result ? 1 : 0);
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int getVariableOrValue(String str, VariableStorage variableStorage) {
        if (variableStorage.contains(str)) { // Use the passed variableStorage instance
            return variableStorage.getVariable(str);
        }
        return Integer.parseInt(str);
    }
}
