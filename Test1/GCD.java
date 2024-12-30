public class GCD {

    public int calculate(String expression, VariableStorage variableStorage) {
        String params = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        String[] numbers = params.split(",");
        int num1 = getVariableOrValue(numbers[0].trim(), variableStorage);
        int num2 = getVariableOrValue(numbers[1].trim(), variableStorage);
        return gcd(num1, num2);
    }

    public void execute(String line, VariableStorage variableStorage) {
        String varName = line.substring(0, line.indexOf('=')).trim();
        String expression = line.substring(line.indexOf('=') + 1).trim();
        String params = expression.substring(expression.indexOf('(') + 1, expression.indexOf(')')).trim();
        String[] numbers = params.split(",");
        int num1 = getVariableOrValue(numbers[0].trim(), variableStorage);
        int num2 = getVariableOrValue(numbers[1].trim(), variableStorage);
        int result = gcd(num1, num2);
        variableStorage.storeVariable(varName, result);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Pass variableStorage to this method
    private int getVariableOrValue(String str, VariableStorage variableStorage) {
        if (variableStorage.contains(str)) {
            return variableStorage.getVariable(str);
        }
        return Integer.parseInt(str);
    }
}
