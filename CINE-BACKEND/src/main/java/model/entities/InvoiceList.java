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

public class InvoiceList {

    public InvoiceList(List<Invoice> list) {
        this.list = list;
    }

    public InvoiceList() {
        this(new ArrayList<>());
    }

    public List<Invoice> getList() {
        return this.list;
    }

    public void setList(List<Invoice> list) {
        this.list = list;
    }

    public JSONObject toJSON() {
        JSONArray a = new JSONArray();
        list.forEach((invoice) -> {
            a.put(invoice.toJSON());
        });

        JSONObject json = new JSONObject();
        json.put("invoice-list", a);
        return json;
    }

    public JSONObject toJSONbyId(String id) {
        JSONArray a = new JSONArray();
        for (Invoice i : getList()) {
            if (i.getClient().getId().equals(id)) {
                a.put(i.toJSON());
            }
        }

        JSONObject json = new JSONObject();
        json.put("invoice-list", a);
        return json;
    }

    private List<Invoice> list;
}
