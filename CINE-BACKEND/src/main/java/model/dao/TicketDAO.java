package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.crud.TicketCRUD;
import model.entities.Ticket;

public class TicketDAO extends AbstractDAO<Integer, Ticket> {

    public TicketDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public TicketDAO() throws IOException {
        this(new DaoDB(), new TicketCRUD());
    }

    @Override
    public Ticket getRecord(ResultSet rs) throws SQLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddParameters(PreparedStatement stm, Integer id, Ticket value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, Integer id, Ticket value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
