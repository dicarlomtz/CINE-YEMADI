package model.entities;

import java.io.Serializable;

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
    
    
    private String number;
    
}
