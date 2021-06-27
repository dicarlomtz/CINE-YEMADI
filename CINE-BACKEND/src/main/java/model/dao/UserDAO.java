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
import java.util.Objects;
import model.dao.crud.UserCRUD;
import model.entities.Rol;
import model.entities.User;

public class UserDAO extends AbstractDAO<String, User> {

    public UserDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public UserDAO() throws IOException {
        this(new DaoDB(), new UserCRUD());
    }

    public User loginUser(String usuario, String clave)
            throws SQLException, IOException, IllegalArgumentException {
        User user = null;
        UserCRUD ucrud = (UserCRUD) crud;
        try (Connection cnx = db.getConnection();
                PreparedStatement stm = cnx.prepareStatement(ucrud.getLoginCmd())) {
            stm.clearParameters();
            stm.setString(1, usuario);
            stm.setString(2, clave);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    user = getRecord(rs);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException();
        }
        return user;
    }

    @Override
    public User getRecord(ResultSet rs) throws SQLException, IOException {
        return new User(
                rs.getString("id_usuario"),
                new CustomerDAO().retrieve(rs.getString("cliente_id")),
                rs.getString("clave"),
                new Rol(rs.getBoolean("rol"))
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, User value) throws SQLException {
        stm.setString(1, id);
        stm.setString(2, value.getUserClientInfo().getId());
        stm.setString(3, value.getPassword());
        stm.setBoolean(4, value.getRol().isIsAdmin());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, User value) throws SQLException {
        stm.setString(1, value.getUserClientInfo().getId());
        stm.setString(2, value.getPassword());
        stm.setBoolean(3, value.getRol().isIsAdmin());
        stm.setString(4, id);
    }

}
