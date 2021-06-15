package model.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.CinemaDAO;
import model.dao.MovieDAO;
import model.dao.RoomDAO;
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
