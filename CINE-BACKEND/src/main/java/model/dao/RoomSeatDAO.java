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
import model.dao.crud.RoomSeatCRUD;
import model.entities.Room;
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
                rs.getBoolean("disponible")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, RoomSeat value) throws SQLException {
        stm.setInt(1, value.getCinema().getId());
        stm.setInt(2, value.getRoom().getNumber());
        String d = String.valueOf(value.getRow());
        stm.setString(3, d);
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

    public List<RoomSeat> listCinemaRoomSeats(Integer idCinema, Integer roomNumber)
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
    
    public void addRoomSeats(Room value)
            throws SQLException {

        try {
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
            for (int i = 1, r = 0, c = 0; i <= value.getCapacity(); i++, c++) {
                if (c == 14) {
                    r++;
                    if (r >= 9) {
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

}
