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
import model.dao.crud.InvoiceCRUD;
import model.entities.Invoice;
import model.entities.PaymentCard;

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

}
