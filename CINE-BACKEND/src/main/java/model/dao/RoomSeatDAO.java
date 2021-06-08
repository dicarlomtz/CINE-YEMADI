package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.crud.RoomSeatCRUD;
import model.entities.RoomSeat;

public class RoomSeatDAO extends AbstractDAO<String, RoomSeat> {

    public RoomSeatDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public RoomSeatDAO() throws IOException {
        this(new DaoDB(), new RoomSeatCRUD());
    }

    @Override
    public RoomSeat getRecord(ResultSet rs) throws SQLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, RoomSeat value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, RoomSeat value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
