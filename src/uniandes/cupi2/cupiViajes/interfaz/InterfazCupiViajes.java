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
package uniandes.cupi2.cupiViajes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.ComparadorReservaNombreCliente;
import uniandes.cupi2.cupiViajes.mundo.CriterioOrdenReserva;
import uniandes.cupi2.cupiViajes.mundo.CupiViajes;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;
import uniandes.cupi2.ordenador.AlgoritmoOrdenamiento;
import uniandes.cupi2.ordenador.Ordenador;

/**
 * Ventana principal de la aplicación.
 */
public class InterfazCupiViajes extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa la ubicación del archivo con los datos de los hoteles.
     */
    private final static String ARCHIVO = "./data/viajes.properties";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private CupiViajes cupiViajes;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Panel con la imagen del encabezado.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con la información del hotel de la reserva.
     */
    private PanelHotel panelHotel;

    /**
     * Panel con la información de la reserva.
     */
    private PanelReserva panelReserva;

    /**
     * Panel con las opciones de búsqueda y extensión.
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel con la lista de reservas hechas.
     */
    private PanelListaReservas panelListaReservas;

    private Ordenador<ReservaViaje> ordenadorReservas;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal.<br>
     * <b> post: </b> Construye la ventana principal de la aplicación.
     */
    public InterfazCupiViajes( )
    {
        cupiViajes = new CupiViajes( );

        setLayout( new BorderLayout( ) );
        setTitle( "CupiViajes" );
        setSize( new Dimension( 750, 650 ) );
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo( null );
        setResizable( true );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        JPanel panelAux = new JPanel( );
        panelAux.setLayout( new BorderLayout( ) );
        panelAux.setBorder( new TitledBorder( "Información de la reserva" ) );

        panelReserva = new PanelReserva( );
        panelAux.add( panelReserva, BorderLayout.CENTER );

        panelHotel = new PanelHotel( this );
        panelAux.add( panelHotel, BorderLayout.SOUTH );


        add( panelAux, BorderLayout.CENTER );

        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, BorderLayout.SOUTH );

        panelListaReservas = new PanelListaReservas( this );
        add( panelListaReservas, BorderLayout.WEST );

        cargarHoteles( );
        //REVIEW Parte 4 Punto 2a Inicialice el atributo ordenador reservas
        ordenadorReservas = new Ordenador<ReservaViaje>();

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Carga la información de los hoteles a partir de un archivo properties.
     */
    private void cargarHoteles( )
    {
        FileInputStream fis;
        try
        {
            fis = new FileInputStream( new File( ARCHIVO ) );
            InputStreamReader in = new InputStreamReader(fis,"UTF8");
            Properties propiedades = new Properties( );
            //propiedades.load( fis );
            propiedades.load(in);
            String aux = propiedades.getProperty( "total.hoteles" );
            int numHoteles = Integer.parseInt( aux );

            String dato;
            String ciudad;
            String hotel;
            int estrellas;
            double costoNoche;
            String imagen;

            for( int i = 1; i <= numHoteles; i++ )
            {
                ciudad = propiedades.getProperty( "hotel" + i + ".ciudad" );
                hotel = propiedades.getProperty( "hotel" + i + ".hotel" );
                dato = propiedades.getProperty( "hotel" + i + ".estrellas" );
                estrellas = Integer.parseInt( dato );
                dato = propiedades.getProperty( "hotel" + i + ".costoNoche" );
                costoNoche = Double.parseDouble( dato );
                imagen = propiedades.getProperty( "hotel" + i + ".imagen" );

                cupiViajes.agregarHotel( hotel, ciudad, estrellas, costoNoche, imagen );
            }

        }
        catch( FileNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, "No se encuentra el archivo a cargar.", "CupiViajes", JOptionPane.ERROR_MESSAGE );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el archivo de propiedades.", "CupiViajes", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Retorna la lista de hoteles disponibles en el sistema.
     * @return Lista de hoteles disponibles.
     */
    public ArrayList<Hotel> darHoteles( )
    {
        return cupiViajes.darHoteles( );
    }

    /**
     * Actualiza la lista de reservas.
     */
    public void actualizarListaReservas( )
    {
        panelListaReservas.actualizarLista( cupiViajes.darReservas( ) );
    }

    /**
     * Actualiza la información de la reserva y del hotel dados por parámetro.
     * @param pReserva Reserva que se va a visualizar. pReserva != null.
     * @param pHotel Hotel que se va a visualizar. pHotel != null.
     */
    public void actualizarInformacion( ReservaViaje pReserva, Hotel pHotel )
    {
        panelReserva.actualizarInformacion( pReserva );
        panelHotel.actualizar( pHotel );
    }

    /**
     * Retorna la lista de hoteles que se encuentran en la ciudad dada por parámetro.
     * @param pCiudad Ciudad de los hoteles buscados. pCiudad != null && pCiudad != "".
     * @return Lista de hoteles que se encuentran en la ciudad dada.
     */
    public ArrayList buscarHotelesPorCiudad( String pCiudad )
    {
        return cupiViajes.buscarHotelesCiudad( pCiudad );
    }

    /**
     * Busca la reserva según el criterio dado por parámetro. <br>
     * <b> post: </b> Se actualiza la información de los paneles con la información de la reserva.
     * @param pCriterio Criterio por el que se va a buscar la reserva. pCriterio != null && pCriterio pertenece a { PanelOpciones.CLIENTE, PanelOpciones.CIUDAD, PanelOpciones.MAYOR_PERSONAS, PanelOpciones.MENOR_PERSONAS }.
     */
    public void buscarReserva( String pCriterio )
    {
        if( pCriterio.equals( PanelOpciones.CLIENTE ) )
        {
            ordenadorReservas.ordenar( AlgoritmoOrdenamiento.BURBUJA, cupiViajes.darReservas( ), true, new ComparadorReservaNombreCliente( ) );
            String cliente = JOptionPane.showInputDialog( this, "Por favor indique el nombre del cliente: " );
            if( cliente != null && !cliente.equals( "" ))
            {
                ReservaViaje reserva = cupiViajes.buscarReservaPorClienteBinario( cliente );
                if( reserva != null )
                {
                    panelReserva.actualizarInformacion( reserva );
                    panelHotel.actualizar( reserva.darHotel( ) );
                    panelListaReservas.seleccionarReserva( reserva );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "No se encontró una reserva con el cliente dado.", "Buscar reserva por cliente", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El nombre del cliente no puede ser vacío.", "Buscar reserva por cliente", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( pCriterio.equals( PanelOpciones.CIUDAD ) )
        {
            String ciudad = JOptionPane.showInputDialog( this, "Por favor indique el nombre de la ciudad: " );
            if( ciudad != null && !ciudad.equals( "" ) )
            {
                ReservaViaje reserva = cupiViajes.buscarReservaPorCiudad( ciudad );
                if( reserva != null )
                {
                    panelReserva.actualizarInformacion( reserva );
                    panelHotel.actualizar( reserva.darHotel( ) );
                    panelListaReservas.seleccionarReserva( reserva );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "No se encontró una reserva con la ciudad dada.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El nombre de la ciudad no puede ser vacía.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( pCriterio.equals( PanelOpciones.AEROLINEA ) )
        {
            Aerolinea[] opciones = Aerolinea.values( );
            JComboBox comboOpciones = new JComboBox( opciones );
            JOptionPane.showMessageDialog( null, comboOpciones, "¿Por qué aerolínea desea buscar la reserva?", JOptionPane.QUESTION_MESSAGE );
            Aerolinea aerolinea = ( Aerolinea )comboOpciones.getSelectedItem( );
            if( aerolinea != null )
            {
                ArrayList<ReservaViaje> listaReservas = cupiViajes.buscarReservasAerolinea( aerolinea );
                panelListaReservas.actualizarLista( listaReservas );
            }
        }
        else if( pCriterio.equals( PanelOpciones.MAYOR_PERSONAS ) )
        {
            ReservaViaje reserva = cupiViajes.buscarReservaMasPersonas( );
            if( reserva != null )
            {
                panelReserva.actualizarInformacion( reserva );
                panelHotel.actualizar( reserva.darHotel( ) );
                panelListaReservas.seleccionarReserva( reserva );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No hay reservas en el sistema.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( pCriterio.equals( PanelOpciones.MENOR_PERSONAS ) )
        {
            ReservaViaje reserva = cupiViajes.buscarReservaMenosPersonas( );
            if( reserva != null )
            {
                panelReserva.actualizarInformacion( reserva );
                panelHotel.actualizar( reserva.darHotel( ) );
                panelListaReservas.seleccionarReserva( reserva );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No hay reservas en el sistema.", "Buscar reserva por ciudad", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Guarda una reserva en el sistema con los valores dados por parámetro.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pFechaLlegada fecha de llegada al hotel que se va a reservar. pDiaLlegada !=null
     * @param pCliente Cliente responsable de la reserva. pCliente != null && pCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar al hotel que se va a reservar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de niños que van a viajar al hotel que se va a reservar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estadía en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerolínea que se va a reservar. pAerolinea != "" && pAerolinea pertenece a {ReservaViaje.AVIANCA, ReservaViaje.LAN, ReservaViaje.VIVA_COLOMBIA,
     *        ReservaViaje.SATENA, ReservaViaje.JETBLUE, ReservaViaje.IBERIA, ReservaViaje.AIR_FRANCE}.
     */
    public void guardarReserva( Hotel pHotel, Date pFechaLlegada, String pCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea )
    {
        boolean agrego = cupiViajes.agregarReserva( pHotel, pFechaLlegada, pCliente, pCantidadAdultos, pCantidadNinios, pNochesEstadia, pAerolinea );
        if( agrego )
        {
            actualizarListaReservas( );
            actualizarInformacion( cupiViajes.buscarReserva( pCliente ), pHotel );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No se pudo guardar la reserva: El cliente ya tiene una reserva a su nombre.", "Guardar reserva", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Crea y visualiza el diálogo para guardar una reserva.
     */
    public void iniciarDialogoGuardarReserva( )
    {
        DialogoAgregarReserva dialogo = new DialogoAgregarReserva( this );
        dialogo.setVisible( true );
    }

    /**
     * Crea y visualiza el diálogo para seleccionar el hotel de la reserva.
     * @param pDialogoReserva Referencia al diálogo de reserva. pDialogoReserva != null.
     */
    public void iniciarDialogoSeleccionarHotel( DialogoAgregarReserva pDialogoReserva )
    {
        DialogoSeleccionHotel dialogo = new DialogoSeleccionHotel( this, pDialogoReserva );
        dialogo.inicializarCiudades( cupiViajes.darHoteles( ) );
        dialogo.setVisible( true );
    }

    /**
     * Ordena la lista de reservas según el criterio dado por parámetro. <br>
     * <b> post: </b> La lista de reservas quedó ordenada según el criterio.
     */
    public void ordenarLista( )
    {
        CriterioOrdenReserva[] opciones = CriterioOrdenReserva.values( );
        JComboBox comboOpciones = new JComboBox( opciones );
        JOptionPane.showMessageDialog( null, comboOpciones, "¿Según que criterio ordenar la lista?", JOptionPane.QUESTION_MESSAGE );
        CriterioOrdenReserva opcion = ( CriterioOrdenReserva )comboOpciones.getSelectedItem( );

        //REVIEW Parte 6 Punto 2a Cree un JComboBox llamado comboAlgoritmos con los posibles algoritmos de ordenamientos
        //      Use como referencia el comboOpciones (creado en este mismo método)
        JComboBox comboAlgoritmos = new JComboBox( opciones );



        JOptionPane.showMessageDialog( null, comboAlgoritmos, "¿Cómo desea ordenar la lista?", JOptionPane.QUESTION_MESSAGE );
        AlgoritmoOrdenamiento algoritmo = ( AlgoritmoOrdenamiento )comboAlgoritmos.getSelectedItem( );


        if( opcion != null && algoritmo !=null)
        {
            ordenadorReservas.ordenar( algoritmo, cupiViajes.darReservas( ), opcion.esAscendnete( ), opcion.darComparador( ) );
            actualizarListaReservas( );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cupiViajes.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupiViajes.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }


    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param pArgs Argumentos para la ejecución de la aplicación. pArgs != null.
     */
    public static void main( String[] pArgs )
    {
        InterfazCupiViajes interfaz = new InterfazCupiViajes( );
        interfaz.setVisible( true );

    }

}
