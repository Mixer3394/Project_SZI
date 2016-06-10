/**
 * Created by mariusz on 10/06/16.
 */
package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CasesBase {
    private Map<Integer, List<String>> caseBase;

    public CasesBase() {
        caseBase = new HashMap<>();
    }

    public void addData(Integer key, String property) {
        if (caseBase.containsKey(key)) {
            addDataToKey(key, property);
        } else {
            addNewKey(key, property);
        }
    }

    private void addDataToKey(Integer key, String property) {
        List<String> properties = caseBase.get(key);
        properties.add(property);
        caseBase.put(key, properties);
    }

    private void addNewKey(Integer key, String property) {
        List<String> properties = new ArrayList<>();
        properties.add(property);
        caseBase.put(key, properties);
    }

    public Map<Integer, List<String>> getCasesBase() {
        return caseBase;
    }
}
