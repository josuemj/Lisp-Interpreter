import java.util.HashMap;
import java.util.Map;

public class setq {
    private Map<String, Object> variables;

    public setq() {
        variables = new HashMap<>();
    }

    public void setVariable(String variableName, Object value) {
        variables.put(variableName, value);
    }

    public Object getVariable(String variableName) {
        return variables.get(variableName);
    }
}

