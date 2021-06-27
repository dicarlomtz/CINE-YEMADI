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

public class Ticket implements Serializable {

    public Ticket(int id, Invoice invoice, Cinema cinema, Room room, Date date, FunctionSeat seat,
            double amount) {
        this.id = id;
        this.invoice = invoice;
        this.cinema = cinema;
        this.room = room;
        this.date = date;
        this.seat = seat;
        this.amount = amount;
    }

    public Ticket() {
        this(-1, null, null, null, null, null, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FunctionSeat getSeat() {
        return seat;
    }

    public void setSeat(FunctionSeat seat) {
        this.seat = seat;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("id", getId());
        json.put("invoice", getInvoice().toJSON());
        json.put("cinema", getCinema().toJSON());
        json.put("room", getRoom().toJSON());
        json.put("date", getDate().toString());
        json.put("seat", getSeat().toJSON());
        json.put("amount", getAmount());

        return json;
    }

    private int id;
    private Invoice invoice;
    private Cinema cinema;
    private Room room;
    private Date date;
    private FunctionSeat seat;
    private double amount;

}
