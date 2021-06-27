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
import model.dao.crud.CustomerCRUD;
import model.entities.Customer;
import model.entities.PaymentCard;

public class CustomerDAO extends AbstractDAO<String, Customer> {

    public CustomerDAO(Database db, AbstractCRUD crud) {
        super(db, crud);
    }

    public CustomerDAO() throws IOException {
        this(new DaoDB(), new CustomerCRUD());
    }

    @Override
    public Customer getRecord(ResultSet rs) throws SQLException, IOException {
        return new Customer(
                rs.getString("id_cliente"),
                rs.getString("apellidos"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                new PaymentCard(rs.getString("tarjeta_pago"))
        );
    }

    @Override
    public void setAddParameters(PreparedStatement stm, String id, Customer value) throws SQLException {
        stm.setString(1, value.getId());
        stm.setString(2, value.getSurnames());
        stm.setString(3, value.getName());
        stm.setString(4, value.getTelephone());
        stm.setString(5, value.getPaymentCard().getNumber());
    }

    @Override
    public void setUpdateParameters(PreparedStatement stm, String id, Customer value) throws SQLException {
        stm.setString(1, value.getSurnames());
        stm.setString(2, value.getName());
        stm.setString(3, value.getTelephone());
        stm.setString(4, value.getPaymentCard().getNumber());
        stm.setString(5, value.getId());
    }

}
