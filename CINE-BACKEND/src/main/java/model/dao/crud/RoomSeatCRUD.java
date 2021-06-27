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

    public String getListCinemaRoomSeatCmd() {
        return LIST_CINEMA_ROOM_SEAT;
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

    protected static final String LIST_CINEMA_ROOM_SEAT
            = "select "
            + "sala_cinema_id, sala_numero, fila, posicion, disponible "
            + "from bd_cinema.asiento_sala where sala_cinema_id = ? and sala_numero = ? "
            + "order by sala_numero; ";

}
