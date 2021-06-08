package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.crud.CinemaCRUD;
import model.entities.Cinema;

public class CinemaDAO extends AbstractDAO<Integer, Cinema> {

    public CinemaDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public CinemaDAO() throws IOException {
        this(new DaoDB(), new CinemaCRUD());
    }

    @Override
    public Cinema getRecord(ResultSet rs) throws SQLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddParameters(PreparedStatement stm, Integer id, Cinema value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, Integer id, Cinema value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
