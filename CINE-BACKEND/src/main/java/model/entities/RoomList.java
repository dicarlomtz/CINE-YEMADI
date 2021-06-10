package model.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoomList {
    
    public RoomList(List<Room> list){
        this.list = list;
    }
    
    public RoomList(){
        this(new ArrayList<>());
    }

    public List<Room> getList() {
        return list;
    }

    public void setList(List<Room> list) {
        this.list = list;
    }
    
    public JSONObject toJSON(){
        JSONArray a = new JSONArray();
        list.forEach((room) -> {
            a.put(room.toJSON());
        });
        
        JSONObject json = new JSONObject();
        json.put("room-list", a);
        return json;
    }
    
    private List<Room> list;
    
}
