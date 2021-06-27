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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.dao.crud.TicketCRUD;
import model.entities.Ticket;

public class TicketDAO extends AbstractDAO<Integer, Ticket> {

    public TicketDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public TicketDAO() throws IOException {
        this(new DaoDB(), new TicketCRUD());
    }

    @Override
    public Ticket getRecord(ResultSet rs) throws SQLException, IOException {
        String id = String.format("%d-%d-%d-%s-%d",
                rs.getInt("asiento_funcion_sala_cinema_id"),
                rs.getInt("asiento_funcion_sala_numero"),
                rs.getTimestamp("asiento_funcion_fecha").getTime(),
                rs.getString("asiento_funcion_fila"),
                rs.getInt("asiento_funcion_posicion")
        );
        return new Ticket(
                rs.getInt("id_tiquete"),
                new InvoiceDAO().retrieve(rs.getInt("factura_seq")),
                new CinemaDAO().retrieve(rs.getInt("asiento_funcion_sala_cinema_id")),
                new RoomDAO().retrieve(String.format("%d-%d", rs.getInt("asiento_funcion_sala_numero"), rs.getInt("asiento_funcion_sala_cinema_id"))),
                new java.util.Date(rs.getTimestamp("asiento_funcion_fecha").getTime()),
                new FunctionSeatDAO().retrieve(id),
                rs.getDouble("monto")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, Integer id, Ticket value) throws SQLException {
        stm.setInt(1, value.getInvoice().getId());
        stm.setInt(2, value.getCinema().getId());
        stm.setInt(3, value.getRoom().getNumber());
        stm.setTimestamp(4, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(5, String.valueOf(value.getSeat().getRow()));
        stm.setInt(6, value.getSeat().getPosition());
        stm.setDouble(7, value.getAmount());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, Integer id, Ticket value) throws SQLException {
        stm.setInt(1, value.getInvoice().getId());
        stm.setInt(2, value.getCinema().getId());
        stm.setInt(3, value.getRoom().getNumber());
        stm.setTimestamp(4, new Timestamp(((Date) value.getDate()).getTime()));
        stm.setString(5, String.valueOf(value.getSeat().getRow()));
        stm.setInt(6, value.getSeat().getPosition());
        stm.setDouble(7, value.getAmount());
        stm.setInt(8, id);
    }

    public List<Ticket> listInvoiceTickets(Integer invoice)
            throws SQLException, IOException {
        List<Ticket> r = new ArrayList<>();
        TicketCRUD rcrud = (TicketCRUD) getCRUD();
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(rcrud.getListInvoiceTickets())) {
            stm.clearParameters();
            stm.setInt(1, invoice);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getRecord(rs));
                }
            }
        }
        return r;
    }

}
