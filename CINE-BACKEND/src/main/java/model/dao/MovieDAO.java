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
        return new Movie(
                rs.getString("id_pelicula"),
                rs.getString("titulo"),
                rs.getString("poster_path"),
                rs.getString("movie_data"),
                rs.getBoolean("cartelera")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, Movie value) throws SQLException {
        stm.setString(1, id);
        stm.setString(2, value.getTitle());
        stm.setString(3, value.getPosterPath());
        stm.setString(4, value.getData());
        stm.setBoolean(5, value.isBillboard());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, Movie value) throws SQLException {
        stm.setString(1, value.getTitle());
        stm.setString(2, value.getPosterPath());
        stm.setString(3, value.getData());
        stm.setBoolean(4, value.isBillboard());
        stm.setString(5, id);
    }

}
