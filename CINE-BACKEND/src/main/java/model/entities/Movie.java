/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

package model.entities;

import java.io.InputStream;
import java.io.Serializable;
import org.json.JSONObject;

public class Movie implements Serializable {

    public Movie(String id, String title, String data, boolean billboard, String type, InputStream image, int length) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.billboard = billboard;
        this.type = type;
        this.image = image;
        this.length = length;
    }

    public Movie() {
        this(null, null, null, true, null, null, 0);
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isBillboard() {
        return billboard;
    }

    public void setBillboard(boolean billboard) {
        this.billboard = billboard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public int getLength() {
        return length;
    }

    public void getLength(int length) {
        this.length = length;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("title", getTitle());
        json.put("data", getData());
        json.put("billboard", isBillboard());
        json.put("type", getType());
        json.put("length", getLength());
        return json;
    }

    private String id;
    private String title;
    private String data;
    private boolean billboard;
    private String type;
    private InputStream image;
    private int length;

}
