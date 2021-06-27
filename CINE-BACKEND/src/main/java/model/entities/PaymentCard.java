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

public class PaymentCard implements Serializable {

    public PaymentCard(String number) {
        this.number = number;
    }

    public PaymentCard() {
        this(null);
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public JSONObject toJSON(){
        JSONObject r = new JSONObject();
        r.put("number", getNumber());
        return r;
    }
    
    
    private String number;
    
}
