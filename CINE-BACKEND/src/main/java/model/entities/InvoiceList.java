package model.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class InvoiceList
{
    public InvoiceList(List<Invoice> list)
    {
        this.list = list;
    }
    
    public InvoiceList()
    {
        this(new ArrayList<>());
    }
    
    public List<Invoice> getList()
    {
        return this.list;
    }
    
    public void setList(List<Invoice> list)
    {
        this.list = list;
    }
    
    public JSONObject toJSON()
    {
        JSONArray a = new JSONArray();
        list.forEach((invoice)->{
            a.put(invoice.toJSON());
        });
        
        JSONObject json = new JSONObject();
        json.put("invoice-list", a);
        return json;
    }
    
    public JSONObject toJSONbyId(String id)
    {
        List<Invoice> list2 = new ArrayList<>();
        
        for(Invoice i : list)
        {
            if(i.getClient().getId() == id)
            {
                list2.add(i);
            }
        }
        
        JSONArray a = new JSONArray();
        list2.forEach((ticket)->{
            a.put(ticket.toJSON());
        });
        
        JSONObject json = new JSONObject();
        json.put("invoice-list", a);
        return json;
    }
    
    private List<Invoice> list;
}
