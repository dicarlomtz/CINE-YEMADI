package model.dao.crud;

import cr.ac.una.db.dao.crud.AbstractCRUD;

public class MovieCRUD extends AbstractCRUD {

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
            + "id_pelicula, titulo, movie_data, cartelera, tipo_imagen, imagen, tamaño "
            + "from bd_cinema.pelicula order by id_pelicula; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.pelicula "
            + "(id_pelicula, titulo, movie_data, cartelera, tipo_imagen, imagen, tamaño) "
            + "values (?, ?, ?, ?, ?, ?, ?); ";
    
    protected static final String RETRIEVE_CMD
            = "select "
            + "id_pelicula, titulo, movie_data, cartelera, tipo_imagen, imagen, tamaño "
            + "from bd_cinema.pelicula where id_pelicula = ?; ";
    
    protected static final String UPDATE_CMD
            = "update bd_cinema.pelicula "
            + "set titulo = ?, movie_data = ?, cartelera = ?, tipo_imagen = ?, imagen = ?, tamaño = ? "
            + "where id_pelicula = ?; ";
    
    protected static final String DELETE_CMD
            = "delete from bd_cinema.pelicula "
            + "where id_pelicula = ?; ";

}
