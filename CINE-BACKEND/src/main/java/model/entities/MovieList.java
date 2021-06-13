package model.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MovieList {
    
    public MovieList(List<Movie> list){
        this.list = list;
    }
    
    public MovieList(){
        this(new ArrayList<>());
    }

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }
    
    public JSONObject toJSON(){
        JSONArray a = new JSONArray();
        list.forEach((movie) -> {
            a.put(movie.toJSON());
        });
        
        JSONObject json = new JSONObject();
        json.put("room-list", a);
        return json;
    }
    
    private List<Movie> list;
    
}