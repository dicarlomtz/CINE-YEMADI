package model.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FunctionSeatList {

    public FunctionSeatList(List<FunctionSeat> list) {
        this.list = list;
    }

    public FunctionSeatList() {
        this(new ArrayList<>());
    }

    public List<FunctionSeat> getList() {
        return list;
    }

    public void setList(List<FunctionSeat> list) {
        this.list = list;
    }

    public JSONObject toJSON() {
        JSONArray a = new JSONArray();
        list.forEach((fs) -> {
            a.put(fs.toJSON());
        });

        JSONObject json = new JSONObject();
        json.put("function-seat-list", a);
        return json;
    }

    private List<FunctionSeat> list;
}
