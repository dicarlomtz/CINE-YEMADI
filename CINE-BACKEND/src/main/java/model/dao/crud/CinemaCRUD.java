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

public class CinemaCRUD extends AbstractCRUD {

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

    protected static final String LIST_CMD
            = "select "
            + "id_cinema, nombre, direccion "
            + "from bd_cinema.cinema order by id_cinema; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.cinema "
            + "(id_cinema, nombre, direccion) "
            + "values (?, ?, ?); ";
    
    protected static final String RETRIEVE_CMD
            = "select "
            + "id_cinema, nombre, direccion "
            + "from bd_cinema.cinema where id_cinema = ?; ";
    
    protected static final String UPDATE_CMD
            = "update bd_cinema.cinema "
            + "set nombre = ?, direccion = ? "
            + "where id_cinema = ?; ";
    
    protected static final String DELETE_CMD
            = "delete from bd_cinema.cinema "
            + "where id_cinema = ?; ";

}
