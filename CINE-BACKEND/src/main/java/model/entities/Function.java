package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Function implements Serializable {

    public Function(Cinema cinema, Room room, Date date, Movie movie) {
        this.cinema = cinema;
        this.room = room;
        this.date = date;
        this.movie = movie;
    }

    public Function() {
        this(null, null, null, null);
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    private Cinema cinema;
    private Room room;
    private Date date;
    private Movie movie;
    
}
