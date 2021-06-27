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

public class User implements Serializable {

    public User(String id, Customer userClientInfo, String password, Rol rol) {
        this.id = id;
        this.userClientInfo = userClientInfo;
        this.password = password;
        this.rol = rol;
    }

    public User() {
        this(null, null, null, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getUserClientInfo() {
        return userClientInfo;
    }

    public void setUserClientInfo(Customer userClientInfo) {
        this.userClientInfo = userClientInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("customer", getUserClientInfo().toJSON());
        json.put("password", getPassword());
        json.put("rol", getRol().isIsAdmin());
        return json;
    }
    
    private String id;
    private Customer userClientInfo;
    private String password;
    private Rol rol;
    
}
