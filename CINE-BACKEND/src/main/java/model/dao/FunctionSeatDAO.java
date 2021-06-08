package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.crud.FunctionSeatCRUD;
import model.entities.FunctionSeat;

public class FunctionSeatDAO extends AbstractDAO<String, FunctionSeat> {

    public FunctionSeatDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public FunctionSeatDAO() throws IOException {
        this(new DaoDB(), new FunctionSeatCRUD());
    }

    @Override
    public FunctionSeat getRecord(ResultSet rs) throws SQLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, FunctionSeat value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, FunctionSeat value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
