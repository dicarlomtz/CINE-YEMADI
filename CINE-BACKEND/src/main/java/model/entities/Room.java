package model.entities;

import java.io.Serializable;

public class Room implements Serializable {

    public Room(Cinema cinema, int number, int capacity) {
        this.cinema = cinema;
        this.number = number;
        this.capacity = capacity;
    }

    public Room() {
        this(null, -1, -1);
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    private Cinema cinema;
    private int number;
    private int capacity;
    
}
