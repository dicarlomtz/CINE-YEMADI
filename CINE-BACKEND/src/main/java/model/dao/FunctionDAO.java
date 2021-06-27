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
import model.dao.crud.FunctionCRUD;
import model.entities.Function;
import model.entities.FunctionList;
import org.json.JSONObject;

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
            stm.setTimestamp(index++, new Timestamp(Long.parseLong(parameters[2])));
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

    public List<Function> listFunctionBillboard(String idMovie)
            throws SQLException, IOException {
        List<Function> r = new ArrayList<>();
        FunctionCRUD rcrud = (FunctionCRUD) getCRUD();
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(rcrud.getFunctionsBillboardCmd())) {
            stm.clearParameters();
            stm.setString(1, idMovie);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    r.add(getRecord(rs));
                }
            }
        }
        return r;
    }

    public static String FunctionListJSON(String idMovie)
            throws IOException, SQLException {
        FunctionList a = new FunctionList(new FunctionDAO().listFunctionBillboard(idMovie));
        JSONObject json = a.toJSON();
        String b = json.toString(4);
        return b;
    }

}
