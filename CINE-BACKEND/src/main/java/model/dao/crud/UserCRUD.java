/*
    Universidad Nacional de Costa Rica
    Escuela de Informática
    EIF209 Programación IV, ciclo I 2021
    Mauricio Gutiérrez Vásquez 118260119
    Adolfo Di Carlo Martínez Martínez 118050228
    Yeikol Villalobos Herrera 702670531
    Proyecto #2, Cine
*/

package model.dao.crud;

import cr.ac.una.db.dao.crud.AbstractCRUD;

public class UserCRUD extends AbstractCRUD {

    @Override
    public String getListAllCmd() {
        return LIST_CMD;
    }

    @Override
    public String getAddCmd() {
        return ADD_CMD;
    }

    @Override
    public String getRetrieveCmd() {
        return RETRIEVE_CMD;
    }

    @Override
    public String getUpdateCmd() {
        return UPDATE_CMD;
    }

    @Override
    public String getDeleteCmd() {
        return DELETE_CMD;
    }

    public String getLoginCmd() {
        return LOGIN_CMD;
    }

    protected static final String LIST_CMD
            = "select "
            + "id_usuario, cliente_id, clave, rol "
            + "from bd_cinema.usuario order by id_usuario; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.usuario "
            + "(id_usuario, cliente_id, clave, rol) "
            + "values (?, ?, ?, ?); ";

    protected static final String RETRIEVE_CMD
            = "select "
            + "id_usuario, cliente_id, clave, rol "
            + "from bd_cinema.usuario where id_usuario = ?; ";

    protected static final String UPDATE_CMD
            = "update bd_cinema.usuario "
            + "set cliente_id = ?, clave = ?, rol = ? "
            + "where id_usuario = ?; ";

    protected static final String DELETE_CMD
            = "delete from bd_cinema.usuario "
            + "where id_usuario = ?; ";

    protected static final String LOGIN_CMD
            = "select "
            + "id_usuario, cliente_id, clave, rol "
            + "from bd_cinema.usuario where id_usuario = ? and clave = ?; ";

}
