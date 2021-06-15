package model.dao.crud;

import cr.ac.una.db.dao.crud.AbstractCRUD;

public class FunctionCRUD extends AbstractCRUD {

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
            + "sala_cinema_id, sala_numero, fecha, pelicula_id "
            + "from bd_cinema.funcion order by sala_numero; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.funcion "
            + "(sala_cinema_id, sala_numero, fecha, pelicula_id) "
            + "values (?, ?, ?, ?); ";
    
    protected static final String RETRIEVE_CMD
            = "select "
            + "sala_cinema_id, sala_numero, fecha, pelicula_id "
            + "from bd_cinema.funcion where sala_cinema_id = ? and sala_numero = ? and fecha = ?; ";
    
    protected static final String UPDATE_CMD
            = "update bd_cinema.funcion "
            + "set pelicula_id = ? "
            + "where sala_cinema_id = ? and sala_numero = ? and fecha = ?; ";
    
    protected static final String DELETE_CMD
            = "delete from bd_cinema.funcion "
            + "where sala_cinema_id = ? and sala_numero = ? and fecha = ?; ";

}
