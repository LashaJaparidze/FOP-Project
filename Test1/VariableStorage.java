import java.util.HashMap;
import java.util.Map;

public class VariableStorage {
    private final Map<String, Integer> variables = new HashMap<>();

    public void storeVariable(String name, int value) {
        variables.put(name, value);
    }

    public Integer getVariable(String name) {
        return variables.get(name);
    }

    public boolean contains(String name) {
        return variables.containsKey(name);
    }
}
