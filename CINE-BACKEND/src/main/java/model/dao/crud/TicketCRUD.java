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

public class TicketCRUD extends AbstractCRUD {

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

    public String getListInvoiceTickets() {
        return LIST_INVOICE_TICKETS;
    }

    protected static final String LIST_CMD
            = "select "
            + "id_tiquete, factura_seq, asiento_funcion_sala_cinema_id, asiento_funcion_sala_numero, "
            + "asiento_funcion_fecha, asiento_funcion_fila, asiento_funcion_posicion, monto "
            + "from bd_cinema.tiquete order by factura_seq; ";

    protected static final String ADD_CMD
            = "insert into bd_cinema.tiquete "
            + "(factura_seq, asiento_funcion_sala_cinema_id, asiento_funcion_sala_numero, "
            + "asiento_funcion_fecha, asiento_funcion_fila, asiento_funcion_posicion, monto) "
            + "values (?, ?, ?, ?, ?, ?, ?); ";

    protected static final String RETRIEVE_CMD
            = "select "
            + "id_tiquete, factura_seq, asiento_funcion_sala_cinema_id, asiento_funcion_sala_numero, "
            + "asiento_funcion_fecha, asiento_funcion_fila, asiento_funcion_posicion, monto "
            + "from bd_cinema.tiquete where id_tiquete = ?; ";

    protected static final String UPDATE_CMD
            = "update bd_cinema.tiquete "
            + "set factura_seq = ?, asiento_funcion_sala_cinema_id = ?, asiento_funcion_sala_numero = ? "
            + "asiento_funcion_fecha = ?, asiento_funcion_fila = ?, asiento_funcion_posicion = ?, monto = ? "
            + "where id_tiquete = ?; ";

    protected static final String DELETE_CMD
            = "delete from bd_cinema.tiquete "
            + "where id_tiquete = ?; ";

    protected static final String LIST_INVOICE_TICKETS
            = "select "
            + "id_tiquete, factura_seq, asiento_funcion_sala_cinema_id, asiento_funcion_sala_numero, "
            + "asiento_funcion_fecha, asiento_funcion_fila, asiento_funcion_posicion, monto "
            + "from bd_cinema.tiquete where factura_seq = ? order by seq_factura; ";

}
