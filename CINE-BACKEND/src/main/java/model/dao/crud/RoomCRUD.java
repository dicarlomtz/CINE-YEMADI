package model.dao.crud;

import cr.ac.una.db.dao.crud.AbstractCRUD;

public class RoomCRUD extends AbstractCRUD {

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
            + "cinema_id, numero, capacidad "
            + "from bd_cinema.sala order by cinema_id; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.sala "
            + "(cinema_id, numero, capacidad) "
            + "values (?, ?, ?); ";
    
    protected static final String RETRIEVE_CMD
            = "select "
            + "cinema_id, numero, capacidad "
            + "from bd_cinema.sala where numero = ? and cinema_id = ?; ";
    
    protected static final String UPDATE_CMD
            = "update bd_cinema.sala "
            + "set capacidad = ? "
            + "where numero = ? and cinema_id = ?; ";
    
    protected static final String DELETE_CMD
            = "delete from bd_cinema.sala "
            + "where numero = ? and cinema_id = ?; ";

}