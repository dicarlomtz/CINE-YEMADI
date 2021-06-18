package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            i = listAll().size() + 1;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            i++;
        }
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
        stm.setInt(1, id);
        stm.setTimestamp(2, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(3, value.getClient().getId());
        stm.setString(4, value.getPaymentCard().getNumber());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, Integer id, Invoice value) throws SQLException {
        stm.setTimestamp(1, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(1, value.getClient().getId());
        stm.setString(2, value.getPaymentCard().getNumber());
        stm.setInt(4, value.getId());
    }

    public JSONArray doPurchase(JSONObject json) throws IOException, SQLException {

        Invoice in = null;
        if (json.getBoolean("registered")) {
            JSONObject u = json.getJSONObject("user");
            in = new Invoice(i++, new Date(), new CustomerDAO().retrieve(u.getString("id")), new PaymentCard(json.getString("card")));

        } else {
            in = new Invoice(i++, new Date(), new Customer(json.getString("id"), json.getString("surname"), json.getString("name"), "", new PaymentCard(json.getString("card"))), new PaymentCard(json.getString("card")));
        }
        new InvoiceDAO().add(in.getId(), in);
        
        JSONArray ja = new JSONArray();
        ja.put(in.toJSON());

        JSONObject jSeats = json.getJSONObject("seats");
        System.out.println(jSeats);
        System.out.println(json.toString(4));
        List<FunctionSeat> l = new ArrayList<>();
        FunctionSeatDAO fda = new FunctionSeatDAO();
        for (String d : jSeats.keySet()) {
            System.out.println("Key: "+ d);
            FunctionSeat a = new FunctionSeatDAO().retrieve(d);
            a.setAvailable(true);
            Ticket t = new Ticket(0, in, a.getCinema(), a.getRoom(), a.getDate(), a, jSeats.getInt(d));
            new TicketDAO().add(0, t);
            fda.update(a.buildKey(), a);
            ja.put(t.toJSON());
        }

        
        return ja;
    }

    private static int i = 0;

}
