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

public class FunctionSeat implements Serializable {

    public FunctionSeat(Cinema cinema, Room room, Date date, char row, int position, boolean available) {
        this.cinema = cinema;
        this.room = room;
        this.date = date;
        this.row = row;
        this.position = position;
        this.available = available;
    }

    public FunctionSeat() {
        this(null, null, null, ' ', -1, false);
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

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public JSONObject toJSON(){
        JSONObject r = new JSONObject();
        r.put("cinema", getCinema().toJSON());
        r.put("room", getRoom().toJSON());
        r.put("date", getDate().toString());
        r.put("row", String.valueOf(getRow()));
        r.put("position", getPosition());
        r.put("available", isAvailable());
        r.put("key", buildKey());
        return r;
    }

    public String buildKey() {
        return String.format("%d-%d-%d-%c-%d", getCinema().getId(), getRoom().getNumber(), getDate().getTime(), getRow(), getPosition());
    }

    private Cinema cinema;
    private Room room;
    private Date date;
    private char row;
    private int position;
    private boolean available;

}
