package model.entities;

import java.io.Serializable;

public class Client implements Serializable {

    public Client(String id, String surnames, String name, String telephone, PaymentCard paymentCard) {
        this.id = id;
        this.surnames = surnames;
        this.name = name;
        this.telephone = telephone;
        this.paymentCard = paymentCard;
    }

    public Client() {
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
    
    private String id;
    private String surnames;
    private String name;
    private String telephone;
    private PaymentCard paymentCard;
    
}