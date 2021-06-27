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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.crud.CinemaCRUD;
import model.entities.Cinema;

public class CinemaDAO extends AbstractDAO<Integer, Cinema> {

    public CinemaDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public CinemaDAO() throws IOException {
        this(new DaoDB(), new CinemaCRUD());
    }

    @Override
    public Cinema getRecord(ResultSet rs)
            throws SQLException, IOException {
        return new Cinema(
                rs.getInt("id_cinema"),
                rs.getString("nombre"),
                rs.getString("direccion")
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, Integer id, Cinema value)
            throws SQLException {
        stm.setInt(1, id);
        stm.setString(2, value.getName());
        stm.setString(3, value.getAddress());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, Integer id, Cinema value)
            throws SQLException {
        stm.setString(1, value.getName());
        stm.setString(2, value.getAddress());
        stm.setInt(3, id);
    }

}
