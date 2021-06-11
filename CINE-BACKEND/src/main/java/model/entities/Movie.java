package model.entities;

import java.io.Serializable;
import org.json.JSONObject;

public class Movie implements Serializable {

    public Movie(String id, String title, String posterPath, String data) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.data = data;
    }

    public Movie() {
        this(null, null, null, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
     public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("title", getTitle());
        json.put("poster-path", getPosterPath());
        json.put("data", getData());
        return json;
    }
    
    private String id;
    private String title;
    private String posterPath;
    private String data;
    
}
