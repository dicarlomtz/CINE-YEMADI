package model.entities;

import java.io.Serializable;

public class User implements Serializable {

    public User(String id, Client userClientInfo, String password, Rol rol) {
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

    public Client getUserClientInfo() {
        return userClientInfo;
    }

    public void setUserClientInfo(Client userClientInfo) {
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
    
    private String id;
    private Client userClientInfo;
    private String password;
    private Rol rol;
    
}
