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
