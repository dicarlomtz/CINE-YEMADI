package model.dao.crud;

import cr.ac.una.db.dao.crud.AbstractCRUD;

public class FunctionSeatCRUD extends AbstractCRUD {

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
            + "funcion_sala_cinema_id, funcion_sala_numero, funcion_fecha, fila, posicion, ocupado "
            + "from bd_cinema.asiento_funcion order by funcion_sala_cinema_id; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.asiento_funcion "
            + "(funcion_sala_cinema_id, funcion_sala_numero, funcion_fecha, fila, posicion, ocupado) "
            + "values (?, ?, ?, ?, ?, ?); ";
    
    protected static final String RETRIEVE_CMD
            = "select "
            + "funcion_sala_cinema_id, funcion_sala_numero, funcion_fecha, fila, posicion, ocupado "
            + "from bd_cinema.asiento_funcion where funcion_sala_cinema_id = ? and funcion_sala_numero = ? and fila = ? and posicion = ?; ";
    
    protected static final String UPDATE_CMD
            = "update bd_cinema.asiento_funcion "
            + "set ocupado = ? "
            + "where funcion_sala_cinema_id = ? and funcion_sala_numero = ? and funcion_fecha = ? and fila = ? and posicion = ?; ";
    
    protected static final String DELETE_CMD
            = "delete from bd_cinema.asiento_funcion "
            + "where funcion_sala_cinema_id = ? and funcion_sala_numero = ? and funcion_fecha = ? and fila = ? and posicion = ?; ";

}
