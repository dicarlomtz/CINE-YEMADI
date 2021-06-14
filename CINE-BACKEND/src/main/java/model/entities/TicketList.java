package model.entities;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TicketList
{
    public TicketList(List<Ticket> list)
    {
        this.list = list;
    }
    
    public TicketList()
    {
        this(new ArrayList<>());
    }
    
    public List<Ticket> getList()
    {
        return this.list;
    }
    
    public void setList(List<Ticket> list)
    {
        this.list = list;
    }
    
    public JSONObject toJSON()
    {
        JSONArray a = new JSONArray();
        list.forEach((ticket)->{
            a.put(ticket.toJSON());
        });
        
        JSONObject json = new JSONObject();
        json.put("ticket-list", a);
        return json;
    }
    
    private List<Ticket> list;
}
