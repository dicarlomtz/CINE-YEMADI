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

import java.io.Serializable;
import org.json.JSONObject;

public class Cinema implements Serializable {

    public Cinema(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Cinema() {
        this(-1, null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("name", getName());
        json.put("address", getAddress());
        return json;
    }
    
    private int id;
    private String name;
    private String address;
    
}
