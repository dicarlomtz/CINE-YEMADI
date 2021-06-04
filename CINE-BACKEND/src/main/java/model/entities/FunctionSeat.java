package model.entities;

import java.io.Serializable;
import java.util.Date;

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
    
   private Cinema cinema;
   private Room room;
   private Date date;
   private char row;
   private int position;
   private boolean available;
   
}
