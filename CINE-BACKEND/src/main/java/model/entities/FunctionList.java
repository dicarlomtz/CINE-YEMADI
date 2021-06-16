package model.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FunctionList {

    public FunctionList(List<Function> list) {
        this.list = list;
    }

    public FunctionList() {
        this(new ArrayList<>());
    }

    public List<Function> getList() {
        return list;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }

    public JSONObject toJSON() {
        JSONArray a = new JSONArray();
        list.forEach((function) -> {
            a.put(function.toJSON());
        });

        JSONObject json = new JSONObject();
        json.put("function-list", a);
        return json;
    }

    private List<Function> list;
}
