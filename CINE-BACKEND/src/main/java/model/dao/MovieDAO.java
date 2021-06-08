package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.crud.MovieCRUD;
import model.entities.Movie;

public class MovieDAO extends AbstractDAO<String, Movie> {

    public MovieDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public MovieDAO() throws IOException {
        this(new DaoDB(), new MovieCRUD());
    }

    @Override
    public Movie getRecord(ResultSet rs) throws SQLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, Movie value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, Movie value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
