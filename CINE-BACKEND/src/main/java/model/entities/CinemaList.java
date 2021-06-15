package model.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class CinemaList {

    public CinemaList(List<Cinema> list) {
        this.list = list;
    }

    public CinemaList() {
        this(new ArrayList<>());
    }

    public List<Cinema> getList() {
        return list;
    }

    public void setList(List<Cinema> list) {
        this.list = list;
    }

    public JSONObject toJSON() {
        JSONArray a = new JSONArray();
        list.forEach((cinema) -> {
            a.put(cinema.toJSON());
        });

        JSONObject json = new JSONObject();
        json.put("cinema-list", a);
        return json;
    }

    private List<Cinema> list;
}
