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

public class MovieList {
    
    public MovieList(List<Movie> list){
        this.list = list;
    }
    
    public MovieList(){
        this(new ArrayList<>());
    }

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }
    
    public JSONObject toJSON(){
        JSONArray a = new JSONArray();
        list.forEach((movie) -> {
            a.put(movie.toJSON());
        });
        
        JSONObject json = new JSONObject();
        json.put("movie-list", a);
        return json;
    }
    
    private List<Movie> list;
    
}
