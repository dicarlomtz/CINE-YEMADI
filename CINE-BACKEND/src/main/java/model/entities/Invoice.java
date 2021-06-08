package model.entities;

import java.io.Serializable;
import java.util.Date;

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
    
    private int id;
    private Date date;
    private Customer client;
    private PaymentCard paymentCard;
    
}
