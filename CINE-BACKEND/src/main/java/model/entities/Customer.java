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

public class Customer implements Serializable {

    public Customer(String id, String surnames, String name, String telephone, PaymentCard paymentCard) {
        this.id = id;
        this.surnames = surnames;
        this.name = name;
        this.telephone = telephone;
        this.paymentCard = paymentCard;
    }

    public Customer() {
        this(null, null, null, null, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }
    
    public JSONObject toJSON(){
        JSONObject r = new JSONObject();
        r.put("id", getId());
        r.put("surnames", getSurnames());
        r.put("name", getName());
        r.put("telephone", getTelephone());
        r.put("payment-card", getPaymentCard().toJSON());
        return r;
    }
    
    private String id;
    private String surnames;
    private String name;
    private String telephone;
    private PaymentCard paymentCard;
    
}
