package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import model.dao.crud.FunctionCRUD;
import model.entities.Function;
import model.entities.FunctionList;

public class FunctionDAO extends AbstractDAO<String, Function> {

    public FunctionDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public FunctionDAO() throws IOException {
        this(new DaoDB(), new FunctionCRUD());
    }

    @Override
    public Function retrieve(String id)
            throws SQLException, IOException {
        Function r = null;
        String[] parameters = id.split("-");
        int index = 1;
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(getCRUD().getRetrieveCmd())) {
            stm.clearParameters();
            stm.setInt(index++, Integer.parseInt(parameters[0]));
            stm.setInt(index++, Integer.parseInt(parameters[1]));
            stm.setTimestamp(index++, new Timestamp(Integer.parseInt(parameters[2])));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = getRecord(rs);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
        return r;
    }

    @Override
    public Function getRecord(ResultSet rs) throws SQLException, IOException {
        return new Function(
                new CinemaDAO().retrieve(rs.getInt("sala_cinema_id")),
                new RoomDAO().retrieve(String.format("%d-%d",
                        rs.getInt("sala_numero"), rs.getInt("sala_cinema_id"))),
                new java.util.Date(rs.getTimestamp("fecha").getTime()),
                new MovieDAO().retrieve(rs.getString("pelicula_id"))
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, Function value) throws SQLException {
        stm.setInt(1, value.getCinema().getId());
        stm.setInt(2, value.getRoom().getNumber());
        stm.setTimestamp(3, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(4, value.getMovie().getId());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, Function value) throws SQLException {
        stm.setString(1, value.getMovie().getId());
        stm.setInt(2, value.getCinema().getId());
        stm.setInt(3, value.getRoom().getNumber());
        stm.setTimestamp(4, new Timestamp(((Date) value.getDate()).getTime()));
    }

    public String FunctionListJSON()
            throws IOException, SQLException {
        return new FunctionList(new FunctionDAO().listAll()).toJSON().toString(4);
    }
}
