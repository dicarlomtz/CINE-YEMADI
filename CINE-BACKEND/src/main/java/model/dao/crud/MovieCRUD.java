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

    public String getBillboardListMovies() {
        return LIST_BILLBOARD_MOVIES;
    }

    protected static final String LIST_CMD
            = "select "
            + "id_pelicula, titulo, movie_data, cartelera, tipo_imagen, imagen, tamano "
            + "from bd_cinema.pelicula order by id_pelicula; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.pelicula "
            + "(id_pelicula, titulo, movie_data, cartelera, tipo_imagen, imagen, tamano) "
            + "values (?, ?, ?, ?, ?, ?, ?); ";

    protected static final String RETRIEVE_CMD
            = "select "
            + "id_pelicula, titulo, movie_data, cartelera, tipo_imagen, imagen, tamano "
            + "from bd_cinema.pelicula where id_pelicula = ?; ";

    protected static final String UPDATE_CMD
            = "update bd_cinema.pelicula "
            + "set titulo = ?, movie_data = ?, cartelera = ?, tipo_imagen = ?, imagen = ?, tamano = ? "
            + "where id_pelicula = ?; ";

    protected static final String DELETE_CMD
            = "delete from bd_cinema.pelicula "
            + "where id_pelicula = ?; ";

    protected static final String LIST_BILLBOARD_MOVIES
            = "select "
            + "id_pelicula, titulo, movie_data, cartelera, tipo_imagen, imagen, tamano "
            + "from bd_cinema.pelicula where cartelera = true; ";

}
