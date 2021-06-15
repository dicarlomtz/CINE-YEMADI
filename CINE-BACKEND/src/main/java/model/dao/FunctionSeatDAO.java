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
import model.dao.crud.FunctionSeatCRUD;
import model.entities.FunctionSeat;
import model.entities.RoomSeat;

public class FunctionSeatDAO extends AbstractDAO<String, FunctionSeat> {

    public FunctionSeatDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public FunctionSeatDAO() throws IOException {
        this(new DaoDB(), new FunctionSeatCRUD());
    }

    @Override
    public FunctionSeat retrieve(String id)
            throws SQLException, IOException {
        FunctionSeat r = null;
        String[] parameters = id.split("-");
        int index = 1;
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(getCRUD().getRetrieveCmd())) {
            stm.clearParameters();
            stm.setInt(index++, Integer.parseInt(parameters[0]));
            stm.setInt(index++, Integer.parseInt(parameters[1]));
            stm.setTimestamp(index++, new Timestamp(Integer.parseInt(parameters[2])));
            stm.setString(index++, parameters[3]);
            stm.setInt(index++, Integer.parseInt(parameters[4]));
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
    public FunctionSeat getRecord(ResultSet rs) throws SQLException, IOException {
        return new FunctionSeat(
                new CinemaDAO().retrieve(rs.getInt("funcion_sala_cinema_id")),
                new RoomDAO().retrieve(String.format("%d-%d", rs.getInt("sala_cinema_id"),
                        rs.getInt("sala_numero"))),
                new java.util.Date(rs.getDate("funcion_fecha").getTime()),
                rs.getString("fila").charAt(0),
                rs.getInt("posicion"),
                rs.getBoolean("ocupado")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, FunctionSeat value) throws SQLException {
        stm.setInt(1, value.getCinema().getId());
        stm.setInt(2, value.getRoom().getNumber());
        stm.setTimestamp(3, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(4, String.valueOf(value.getRow()));
        stm.setInt(5, value.getPosition());
        stm.setBoolean(6, value.isAvailable());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, FunctionSeat value) throws SQLException {
        stm.setBoolean(1, value.isAvailable());
        stm.setInt(2, value.getCinema().getId());
        stm.setInt(3, value.getRoom().getNumber());
        stm.setTimestamp(4, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(5, String.valueOf(value.getRow()));
        stm.setInt(6, value.getPosition());
    }

}
