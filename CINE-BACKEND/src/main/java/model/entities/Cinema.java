package model.entities;

import java.io.Serializable;

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
    
    private int id;
    private String name;
    private String address;
    
}
