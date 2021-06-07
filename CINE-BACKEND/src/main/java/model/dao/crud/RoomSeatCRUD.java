package model.dao.crud;

import cr.ac.una.db.dao.crud.AbstractCRUD;

public class RoomSeatCRUD extends AbstractCRUD {

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
            + "sala_cinema_id, sala_numero, fila, posicion, disponible "
            + "from bd_cinema.asiento_sala order by sala_cinema_id; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.asiento_sala "
            + "(sala_cinema_id, sala_numero, fila, posicion, disponible) "
            + "values (?, ?, ?, ?, ?); ";
    
    protected static final String RETRIEVE_CMD
            = "select "
            + "sala_cinema_id, sala_numero, fila, posicion, disponible "
            + "from bd_cinema.asiento_sala where sala_cinema_id = ? and sala_numero = ? and fila = ? and posicion = ?; ";
    
    protected static final String UPDATE_CMD
            = "update bd_cinema.asiento_sala "
            + "set disponible = ? "
            + "where sala_cinema_id = ? and sala_numero = ? and fila = ? and posicion = ?; ";
    
    protected static final String DELETE_CMD
            = "delete from bd_cinema.asiento_sala "
            + "where sala_cinema_id = ? and sala_numero = ? and fila = ? and posicion = ?; ";

}
