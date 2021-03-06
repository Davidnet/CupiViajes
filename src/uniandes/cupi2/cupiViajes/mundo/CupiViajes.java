/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiViajes.mundo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * Clase que representa CupiViajes. <br>
 * <b> inv: </b> <br>
 * hoteles != null. <br>
 * reservas != null. <br>
 * No pueden existir dos reservas con el mismo cliente.
 */
public class CupiViajes
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Lista de hoteles disponibles en el sistema de viajes.
     */
    private ArrayList<Hotel> hoteles;

    /**
     * Lista de reservas.
     */
    private ArrayList<ReservaViaje> reservas;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Construye un nuevo sistema de viajes sin hoteles y sin reservas.<br>
     * <b> post: </b> Las listas de hoteles y de viajes han sido inicializadas.
     */
    public CupiViajes( )
    {
        hoteles = new ArrayList<Hotel>( );
        reservas = new ArrayList<ReservaViaje>( );
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna la lista de hoteles disponibles en el sistema.
     * @return Lista de hoteles disponibles.
     */
    public ArrayList<Hotel> darHoteles( )
    {
        return hoteles;
    }

    /**
     * Retorna la lista de reservas.
     * @return Lista de reservas.
     */
    public ArrayList<ReservaViaje> darReservas( )
    {
        return reservas;
    }

    /**
     * Agrega una nueva reserva a la lista si no existe una reserva con el cliente dado. <br>
     * <b> pre </b>: El hotel con el nombre dado ya existe.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pFechaLLegada fecha de llegada al hotel que se va a reservar. pDiaLlegada !=null.
     * @param pNombreCliente Nombre del cliente responsable de la reserva. pCliente != null && pCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar al hotel que se va a reservar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de niños que van a viajar al hotel que se va a reservar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estadía en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerolínea que se va a reservar. pAerolinea != null
     * @return True si la reserva se agregó correctamente. False de lo contrario.
     */
    public boolean agregarReserva( Hotel pHotel, Date pFechaLLegada, String pNombreCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea )
    {
        ReservaViaje buscado = buscarReserva( pNombreCliente );
        boolean agregado = false;

        if( buscado == null )
        {
            ReservaViaje reservaAAgregar = new ReservaViaje( pNombreCliente, pCantidadAdultos, pCantidadNinios, pNochesEstadia, pAerolinea, pHotel, pFechaLLegada );
            reservas.add( reservaAAgregar );
            agregado = true;
            verificarInvariante( );
        }

        return agregado;
    }


    /**
     * Agrega un nuevo hotel a la lista de hoteles. <br>
     * @param pNombre Nombre del hotel. pNombre != null && pNombre != "".
     * @param pCiudad Ciudad en la que se encuentra el hotel. pCiudad != null && pCiudad != "".
     * @param pEstrellas Cantidad de estrellas que tiene el hotel. pEstrellas >= 1 && pEstrellas <=5.
     * @param pCostoNoche Costo por noche en el hotel. pCostoNoche >= 1.
     * @param pRutaImagen Ruta de la imagen del hotel. pRutaImagen != null && pRutaImagen != "".
     */
    public void agregarHotel( String pNombre, String pCiudad, int pEstrellas, double pCostoNoche, String pRutaImagen )
    {
        Hotel hotel = new Hotel( pNombre, pCiudad, pEstrellas, pCostoNoche, pRutaImagen );
        hoteles.add( hotel );
    }

    /**
     * Busca los hoteles que están en la ciudad dada por parámetro.
     * @param pCiudad Ciudad  de los hoteles que se están buscando. pCiudad != null && pCiudad != "".
     * @return Lista con los hoteles que están en la ciudad dada.
     */
    public ArrayList<Hotel> buscarHotelesCiudad( String pCiudad )
    {
        //REVIEW Parte 5 Punto 1a Implemente según la documentación
        ArrayList<Hotel> hotelesbuscados = new ArrayList<Hotel>();
        for (Hotel hotel : hoteles) {
            if(hotel.darCiudad().compareTo(pCiudad) == 0){
                hotelesbuscados.add(hotel);
            }
        }
        return hotelesbuscados;
    }

    /**
     * Busca la reserva del cliente dado por parámetro. <br>
     * @param pCliente Cliente se la reserva que se va a buscar. pCliente != null && pCliente != "".
     * @return Reserva de viaje con el cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReserva( String pCliente )
    {
        //REVIEW Parte 5 Punto 1b Implemente según la documentación
        ReservaViaje resultado = null;
        for (ReservaViaje viaje : reservas) {
            String clientetemp = viaje.darNombreCliente();
            if (clientetemp.equals(pCliente)){
                resultado = viaje;
                return  resultado;
            }
        }
        return resultado;

    }

    /**
     * Busca la reserva del cliente dado por parámetro utilizando una búsqueda binaria. <br>
     * <b> pre: </b> La lista de reservas se encuentra ordenada por clientes.
     * @param pNombreCliente Nombre del cliente de la reserva que se va a buscar. pNombreCliente != null && pNombreCliente != "".
     * @return Reserva de viaje del cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReservaPorClienteBinario( String pNombreCliente )
    {
        // REVIEW Parte 5 Punto 1c Implemente según la documentación
        int lo = 0;
        int hi = reservas.size() - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int diference = pNombreCliente.compareTo(reservas.get(mid).darNombreCliente());
            if (diference < 0){
                hi = mid - 1;
            }
            else if (diference > 0){
                lo = mid + 1;
            }
            else return reservas.get(mid);
        }
        return null;


    }

    /**
     * Busca la primera reserva del viaje a la ciudad dada por parámetro.
     * @param pCiudad Ciudad del hotel de la reserva que se va a buscar. pCiudad != null && pCiudad != "";
     * @return Reserva del viaje a la ciudad dada por parámetro.
     */
    public ReservaViaje buscarReservaPorCiudad( String pCiudad )
    {
        ReservaViaje buscado = null;
        ReservaViaje reservaTemp = null;
        boolean encontrado = false;

        for( int i = 0; i < reservas.size( ) && !encontrado; i++ )
        {
            reservaTemp = reservas.get( i );
            if( reservaTemp.darHotel( ).darCiudad( ).equals( pCiudad ) )
            {
                buscado = reservaTemp;
                encontrado = true;
            }
        }

        return buscado;
    }

    /**
     * Busca la reserva con la mayor cantidad de personas que van a viajar.
     * @return Reserva con la mayor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la mayor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMasPersonas( )
    {
        //REVIEW Parte 5 Punto 1d Implemente según la documentación
        ReservaViaje buscado = null;
        ReservaViaje reservaTemp = null;
        boolean encontrado = false;
        if (reservas != null){
            int max = reservas.get(0).darCantidadAdultos() + reservas.get(0).darCantidadNinios();
            for( int i = 0; i < reservas.size( ) && !encontrado; i++ ) {
                int capacidad = 0;
                reservaTemp = reservas.get(i);
                capacidad = reservaTemp.darCantidadAdultos() + reservaTemp.darCantidadNinios();
                if (capacidad > max){
                    buscado = reservaTemp;
                    max = capacidad;
                    encontrado = true;
                }
            }
        }
        return buscado;
    }

    /**
     * Busca la reserva con la menor cantidad de personas que van a viajar.
     * @return Reserva con la menor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la menor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMenosPersonas( )
    {
        //Review Parte 5 Punto 1e Implemente según la documentación
        ReservaViaje buscado = null;
        ReservaViaje reservaTemp = null;
        boolean encontrado = false;
        if (reservas != null){
            buscado = reservas.get(0);
            int min = reservas.get(0).darCantidadAdultos() + reservas.get(0).darCantidadNinios();
            for( int i = 0; i < reservas.size( ) && !encontrado; i++ ) {
                int capacidad = 0;
                reservaTemp = reservas.get(i);
                capacidad = reservaTemp.darCantidadAdultos() + reservaTemp.darCantidadNinios();
                if (capacidad < min){
                    buscado = reservaTemp;
                    min = capacidad;
                    encontrado = true;
                }
            }
        }
        return buscado;
    }

    /**
     * Busca las reservas con aerolínea dada por parámetro.
     * @param pAerolinea Aerolínea de las reservas que se están buscando. pAerolinea != "" && pAerolinea pertenece a {ReservaViaje.AVIANCA, ReservaViaje.LAN,
     *        ReservaViaje.VIVA_COLOMBIA, ReservaViaje.SATENA, ReservaViaje.JETBLUE, ReservaViaje.IBERIA, ReservaViaje.AIR_FRANCE}.
     * @return Lista de reservan con aerolínea dada.
     */
    public ArrayList<ReservaViaje> buscarReservasAerolinea( Aerolinea pAerolinea )
    {
        ArrayList<ReservaViaje> respuesta = new ArrayList<ReservaViaje>( );
        ReservaViaje reservaTemp = null;

        for( int i = 0; i < reservas.size( ); i++ )
        {
            reservaTemp = reservas.get( i );
            if( reservaTemp.darAerolinea( ).equals( pAerolinea ) )
            {
                respuesta.add( reservaTemp );
            }
        }

        return respuesta;
    }

    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * hoteles != null. <br>
     * reservas != null. <br>
     * No pueden existir dos reservas con el mismo cliente.
     */
    private void verificarInvariante( )
    {
        assert ( hoteles != null ) : "La lista de hoteles no puede ser nula.";
        assert ( reservas != null ) : "La lista de reservas no puede ser nula.";
        assert ( !buscarReservasMismoCliente( ) ) : "No pueden existir reservas con el mismo cliente.";
    }

    /**
     * Verifica si hay dos reservas con el mismo cliente.
     * @return True si hay dos reservas con el mismo cliente. False de lo contrario.
     */
    private boolean buscarReservasMismoCliente( )
    {
        boolean hay = false;
        for( int i = 0; i < reservas.size( ) && !hay; i++ )
        {
            ReservaViaje reservaI = ( ReservaViaje )reservas.get( i );
            for( int j = i + 1; j < reservas.size( ) && !hay; j++ )
            {
                ReservaViaje reservaJ = ( ReservaViaje )reservas.get( j );
                if( reservaI.darNombreCliente( ).equals( reservaJ.darNombreCliente( ) ) )
                {
                    hay = true;
                }
            }
        }
        return hay;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * Método para la extensión 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }

}
