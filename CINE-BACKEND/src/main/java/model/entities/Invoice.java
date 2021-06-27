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
import java.util.Date;
import org.json.JSONObject;

public class Invoice implements Serializable {

    public Invoice(int id, Date date, Customer client, PaymentCard paymentCard) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.paymentCard = paymentCard;
    }

    public Invoice() {
        this(-1, null, null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
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
        r.put("date", getDate().toString());
        r.put("customer", getClient().toJSON());
        r.put("payment-card", getPaymentCard().toJSON());
        return r;
    }
    
    private int id;
    private Date date;
    private Customer client;
    private PaymentCard paymentCard;
    
}
