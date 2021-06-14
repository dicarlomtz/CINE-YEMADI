package model.dao;

import cr.ac.una.db.Database;
import cr.ac.una.db.dao.AbstractDAO;
import cr.ac.una.db.dao.crud.AbstractCRUD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.crud.UserCRUD;
import model.entities.User;

public class UserDAO extends AbstractDAO<String, User> {

    public UserDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public UserDAO() throws IOException {
        this(new DaoDB(), new UserCRUD());
    }
    
    public User loginUser(String usuario, String clave) {
        boolean found = false;
        User user;
        try (Connection cnx = db.getConnection()) {
            UserCRUD ucrud = (UserCRUD) crud;
            try (PreparedStatement stm = cnx.prepareStatement(ucrud.getRetrieveCmd())) {
                stm.clearParameters();
                stm.setString(1, usuario);
                stm.setString(2, clave);
                ResultSet rs = stm.executeQuery();
                found = rs.next();
                user = (User)rs;
                return user;
            }

        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return null;
    }

    @Override
    public User getRecord(ResultSet rs) throws SQLException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, User value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, User value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
