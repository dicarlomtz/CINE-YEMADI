/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

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
import model.dao.crud.RoomCRUD;
import model.entities.Room;

public class RoomDAO extends AbstractDAO<String, Room> {

    public RoomDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public RoomDAO() throws IOException {
        this(new DaoDB(), new RoomCRUD());
    }

    @Override
    public Room retrieve(String id)
            throws SQLException, IOException {
        Room r = null;
        String[] parameters = id.split("-");
        int index = 1;
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(getCRUD().getRetrieveCmd())) {
            stm.clearParameters();
            for (String parameter : parameters) {
                stm.setInt(index++, Integer.parseInt(parameter));
            }
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
    public Room getRecord(ResultSet rs)
            throws SQLException, IOException {
        return new Room(
                new CinemaDAO().retrieve(rs.getInt("cinema_id")),
                rs.getInt("numero"),
                rs.getInt("capacidad")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, Room value)
            throws SQLException {
            stm.setInt(1, value.getCinema().getId());
            stm.setInt(2, value.getNumber());
            stm.setInt(3, value.getCapacity());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, Room value)
            throws SQLException {
        stm.setInt(1, value.getCapacity());
        stm.setInt(2, value.getNumber());
        stm.setInt(3, value.getCinema().getId());
    }

    public List<Room> listCinemaRoom(Integer idCinema)
            throws SQLException, IOException {
        List<Room> r = new ArrayList<>();
        RoomCRUD rcrud = (RoomCRUD) getCRUD();
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(rcrud.getListCinemaRoomCmd())) {
            stm.clearParameters();
            stm.setInt(1, idCinema);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getRecord(rs));
                }
            }
        }
        return r;
    }

}
