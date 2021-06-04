package model.entities;

import java.io.Serializable;

public class Rol implements Serializable {

    public Rol(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Rol() {
        this(false);
    }
    
    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    private boolean isAdmin;
    
}
