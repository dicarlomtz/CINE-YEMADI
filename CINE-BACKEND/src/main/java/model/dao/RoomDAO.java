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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.crud.RoomCRUD;
import model.entities.Room;
import model.entities.RoomSeat;

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
        try {
            stm.setInt(1, value.getCinema().getId());
            stm.setInt(2, value.getNumber());
            stm.setInt(3, value.getCapacity());
            
            RoomSeatDAO rmsd = new RoomSeatDAO();
            char[] row = new char[10];
            row[0] = 'A';
            row[1] = 'B';
            row[2] = 'C';
            row[3] = 'D';
            row[4] = 'E';
            row[5] = 'F';
            row[6] = 'G';
            row[7] = 'H';
            row[8] = 'I';
            row[9] = 'J';
            for (int i = 0, r = 0, c = 0; i < value.getCapacity(); i++, c++) {
                if (c == 25) {
                    r++;
                    if (r == 9) {
                        r = 0;
                    }
                    c = 0;
                }
                RoomSeat rs = new RoomSeat(value.getCinema(), value, row[r], i, true);
                rmsd.add(rs.buildKey(), rs);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
            throw new SQLException();
        }
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
