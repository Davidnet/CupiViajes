package uniandes.cupi2.cupiViajes.mundo;

import java.util.Comparator;

/**
 * Comparador de reservas por Costo.
 * @author David Cardozo
 */
public class ComparadorReservaCosto implements Comparator<ReservaViaje> {
    /**
     * Compara dos reservas por cantidad de personas que van a viajar.
     * @param r1 Reserva 1 para comparar. pReserva != null.
     * @param r2 Reserva 2 para comparar. pReserva != null.
     * @return Retorna 0 si las reservas tienen el mismo costo. <br>
     *         Retorna -1 si la reserva r2 tiene MAYOR costo que r1. <br>
     *         Retorna 1 si la reserva r2 tiene MENOR costo que r1.
     */
    public int compare( ReservaViaje r1, ReservaViaje r2 ){
        if (r1.darCostoTotal() == r2.darCostoTotal()){
            return 0;
        }
        if (r1.darCostoTotal() < r2.darCostoTotal()){
            return -1;
        }
        return 1;
    }

}
