package model.entities;

import java.io.Serializable;

public class RoomSeat implements Serializable {

    public RoomSeat(Cinema cinema, Room room, char row, int position, boolean available) {
        this.cinema = cinema;
        this.room = room;
        this.row = row;
        this.position = position;
        this.available = available;
    }

    public RoomSeat() {
        this(null, null, ' ', -1, false);
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
    private char row;
    private int position;
    private boolean available;
    
}
