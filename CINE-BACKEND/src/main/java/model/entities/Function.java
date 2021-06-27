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

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("cinema", getCinema().toJSON());
        json.put("room", getRoom().toJSON());
        json.put("date", getDate().toString());
        json.put("movie", getMovie().toJSON());
        json.put("key", buildKey());
        return json;
    }

    public String buildKey() {
        return String.format("%d-%d-%d", getCinema().getId(), getRoom().getNumber(), getDate().getTime());
    }

    private Cinema cinema;
    private Room room;
    private Date date;
    private Movie movie;

}
