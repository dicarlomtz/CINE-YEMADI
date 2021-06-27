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

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class CinemaList {

    public CinemaList(List<Cinema> list) {
        this.list = list;
    }

    public CinemaList() {
        this(new ArrayList<>());
    }

    public List<Cinema> getList() {
        return list;
    }

    public void setList(List<Cinema> list) {
        this.list = list;
    }

    public JSONObject toJSON() {
        JSONArray a = new JSONArray();
        list.forEach((cinema) -> {
            a.put(cinema.toJSON());
        });

        JSONObject json = new JSONObject();
        json.put("cinema-list", a);
        return json;
    }

    private List<Cinema> list;
}
