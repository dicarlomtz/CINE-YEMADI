/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import model.dao.crud.InvoiceCRUD;
import model.entities.Customer;
import model.entities.FunctionSeat;
import model.entities.Invoice;
import model.entities.PaymentCard;
import model.entities.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

public class InvoiceDAO extends AbstractDAO<Integer, Invoice> {

    public InvoiceDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public InvoiceDAO() throws IOException {
        this(new DaoDB(), new InvoiceCRUD());
    }

    @Override
    public Invoice getRecord(ResultSet rs) throws SQLException, IOException {
        return new Invoice(
                rs.getInt("seq_factura"),
                new java.util.Date(rs.getTimestamp("fecha").getTime()),
                new CustomerDAO().retrieve(rs.getString("cliente_id")),
                new PaymentCard(rs.getString("tarjeta_pago"))
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, Integer id, Invoice value) throws SQLException {
        stm.setTimestamp(1, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(2, value.getClient().getId());
        stm.setString(3, value.getPaymentCard().getNumber());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, Integer id, Invoice value) throws SQLException {
        stm.setTimestamp(1, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(1, value.getClient().getId());
        stm.setString(2, value.getPaymentCard().getNumber());
        stm.setInt(4, value.getId());
    }

    public JSONObject doPurchase(JSONObject json) throws IOException, SQLException {

        Invoice in = null;
        
        if (json.getBoolean("registered")) {
            JSONObject u = json.getJSONObject("user");
            in = new Invoice(0, new Date(), new CustomerDAO().retrieve(u.getString("id")), new PaymentCard(json.getString("card")));

        } else {
            JSONObject u = json.getJSONObject("user");
            Customer c = new Customer(u.getString("id"), u.getString("surname"), u.getString("name"), "", new PaymentCard(json.getString("card")));
            try {
                new CustomerDAO().add(c.getId(), c);
            } catch (SQLException | IOException | IllegalArgumentException ex) {

            }
            in = new Invoice(0, new Date(), c, new PaymentCard(json.getString("card")));
        }
        
        new InvoiceDAO().add(in.getId(), in);
        
        List<Invoice> invoices = listAll();
        in.setId(invoices.get(invoices.size() - 1).getId());
        
        JSONArray ja = new JSONArray();
        JSONObject jSeats = json.getJSONObject("seats");
        

        FunctionSeatDAO fda = new FunctionSeatDAO();
        
        for (String d : jSeats.keySet()) {
            
            FunctionSeat a = new FunctionSeatDAO().retrieve(d);
            a.setAvailable(true);
            
            Ticket t = new Ticket(0, in, a.getCinema(), a.getRoom(), a.getDate(), a, jSeats.getInt(d));
            
            new TicketDAO().add(0, t);
            
            fda.update(a.buildKey(), a);
            ja.put(t.toJSON());
            
        }
        
        JSONObject res = new JSONObject();
        
        res.put("invoice", in.toJSON());
        res.put("tickets", ja);

        return res;
    }

}
