package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

/**
 * Comparador de reservas por nombre del cliente responsable
 * @author alvar-go
 *
 */
public class ComparadorReservaNombreCliente implements Comparator<ReservaViaje>
{


    /**
     * Compara dos reservas por el cliente responsable.
     * @param r1 Reserva 1 para comparar. pReserva != null.
     * @param r2 Reserva 2 para comparar. pReserva != null.
     * @return Retorna 0 si las reservas tienen el mismo cliente responsable. <br>
     *         Retorna -1 si la reserva r2 tiene un valor "MAYOR" para el nombre cliente que r1. <br>
     *         Retorna 1 si la reserva  r2 tiene un valor "MENOR" para el nombre cliente que r1.
     */
    public int compare( ReservaViaje r1, ReservaViaje r2 )
    {
        // REVIEW Parte 2 punto 2a Implemente según la documentación
        if (r1.darNombreCliente().compareTo(r2.darNombreCliente()) == 0){
            return 0;
        }
        if (r1.darNombreCliente().compareTo(r2.darNombreCliente()) < 0){
            return -1;
        }
        return  1;
    }

}
