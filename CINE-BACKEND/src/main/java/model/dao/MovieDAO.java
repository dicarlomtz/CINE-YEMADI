package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                rs.getString("movie_data"),
                rs.getBoolean("cartelera"),
                rs.getString("tipo_imagen"),
                rs.getBinaryStream("imagen"),
                rs.getInt("tamaño")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, Movie value) throws SQLException {
        stm.setString(1, value.getId());
        stm.setString(2, value.getTitle());
        stm.setString(3, value.getData());
        stm.setBoolean(4, value.isBillboard());
        stm.setString(5, value.getType());
        stm.setBinaryStream(6, value.getImage(), value.getLength());
        stm.setInt(7, value.getLength());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, Movie value) throws SQLException {
        stm.setString(1, value.getTitle());
        stm.setString(2, value.getData());
        stm.setBoolean(3, value.isBillboard());
        stm.setString(4, value.getType());
        stm.setBinaryStream(5, value.getImage(), value.getLength());
        stm.setInt(6, value.getLength());
        stm.setString(7, value.getId());
    }

    public static boolean validate(final String fileName) {
        Matcher matcher = PATERN.matcher(fileName);
        return matcher.matches();
    }

    private static final String IMAGE_PATERN
            = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    private static final Pattern PATERN = Pattern.compile(IMAGE_PATERN);

}
