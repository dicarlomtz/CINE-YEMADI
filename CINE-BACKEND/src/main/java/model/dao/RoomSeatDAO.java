package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public RoomSeat retrieve(String id)
            throws SQLException, IOException {
        RoomSeat r = null;
        String[] parameters = id.split("-");
        int index = 1;
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(getCRUD().getRetrieveCmd())) {
            stm.clearParameters();
            stm.setInt(index++, Integer.parseInt(parameters[0]));
            stm.setInt(index++, Integer.parseInt(parameters[1]));
            stm.setString(index++, parameters[2]);
            stm.setInt(index++, Integer.parseInt(parameters[3]));
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
    public RoomSeat getRecord(ResultSet rs) throws SQLException, IOException {
        return new RoomSeat(
                new CinemaDAO().retrieve(rs.getInt("sala_cinema_id")),
                new RoomDAO().retrieve(rs.getInt("sala_numero") + "-" + rs.getInt("sala_cinema_id")),
                rs.getString("fila").charAt(0),
                rs.getInt("posicion"),
                rs.getBoolean("disponuble")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, RoomSeat value) throws SQLException {
        stm.setInt(1, value.getCinema().getId());
        stm.setInt(2, value.getRoom().getNumber());
        stm.setString(3, String.valueOf(value.getRow()));
        stm.setInt(4, value.getPosition());
        stm.setBoolean(5, value.isAvailable());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, RoomSeat value) throws SQLException {
        stm.setBoolean(1, value.isAvailable());
        stm.setInt(2, value.getCinema().getId());
        stm.setInt(3, value.getRoom().getNumber());
        stm.setString(4, String.valueOf(value.getRow()));
        stm.setInt(5, value.getPosition());
    }

    public List<RoomSeat> listCinemaRoom(Integer idCinema, Integer roomNumber)
            throws SQLException, IOException {
        List<RoomSeat> r = new ArrayList<>();
        RoomSeatCRUD rcrud = (RoomSeatCRUD) getCRUD();
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(rcrud.getListCinemaRoomSeatCmd())) {
            stm.clearParameters();
            stm.setInt(1, idCinema);
            stm.setInt(2, roomNumber);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getRecord(rs));
                }
            }
        }
        return r;
    }

}
