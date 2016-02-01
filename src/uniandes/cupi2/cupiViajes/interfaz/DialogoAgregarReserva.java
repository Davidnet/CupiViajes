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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiViajes.mundo.Aerolinea;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;


/**
 * Diálogo para crear y agregar una nueva reserva.
 */
public class DialogoAgregarReserva extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el cambio en la selección del combo box mes.
     */
    private final static String COMBO_MES = "Combo mes";

    /**
     * Constante que representa el comando para guardar una reserva.
     */
    private final static String GUARDAR = "Guardar reserva";

    /**
     * Constante que representa el comando para buscar un hotel para reservar.
     */
    private final static String BUSCAR_HOTEL = "Buscar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiViajes principal;

    /**
     * Hotel seleccionado por el usuario.
     */
    private Hotel hotelSeleccionado;

    /**
     * Botón para guardar la reserva.
     */
    private JButton btnGuardar;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta para el título del diálogo.
     */
    private JLabel lblTitulo;

    /**
     * Botón para seleccionar el hotel.
     */
    private JButton btnSeleccionarHotel;

    /**
     * Combo box con el día de llegada al hotel.
     */
    private JComboBox cbDia;

    /**
     * Combo box con el mes de llegada al hotel.
     */
    private JComboBox cbMes;

    /**
     * Combo box con el año de llegada al hotel.
     */
    private JComboBox cbAnio;

    /**
     * Combo box con la aerolínea que se va a reservar.
     */
    private JComboBox cbAerolinea;

    /**
     * Combo box con la cantidad de adultos que van a viajar.
     */
    private JComboBox cbCantidadAdultos;

    /**
     * Combo box con la cantidad de niños que van a viajar.
     */
    private JComboBox cbCantidadNinios;

    /**
     * Campo de texto con el cliente que va a hacer la reserva.
     */
    private JTextField txtCliente;

    /**
     * Campo de texto con las noches de estadía.
     */
    private JTextField txtNochesEstadia;

    /**
     * Campo de texto con el nombre del hotel escogido.
     */
    private JTextField txtHotelEscogido;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo para reservar el hotel.
     * <b> post: </b> Todos los elementos gráficos fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public DialogoAgregarReserva( InterfazCupiViajes pPrincipal )
    {
        principal = pPrincipal;

        setTitle( "Crear nueva reserva" );
        setSize( new Dimension( 400, 300 ) );
        setResizable( true );
        setLocationRelativeTo( principal );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        JPanel panel = new JPanel( );
        panel.setLayout( new GridLayout( 9, 2, 5, 5 ) );
        panel.setBorder( new TitledBorder( "Agregar nueva reserva:" ) );

        panel.add( new JLabel( "Hotel:" ) );
        txtHotelEscogido = new JTextField( );
        txtHotelEscogido.setEditable( false );
        panel.add( txtHotelEscogido );

        panel.add( new JLabel( ) );
        btnSeleccionarHotel = new JButton( BUSCAR_HOTEL );
        btnSeleccionarHotel.setActionCommand( BUSCAR_HOTEL );
        btnSeleccionarHotel.addActionListener( this );
        panel.add( btnSeleccionarHotel );

        panel.add( new JLabel( "Nombre del cliente:" ) );
        txtCliente = new JTextField( );
        panel.add( txtCliente );

        panel.add( new JLabel( "Fecha de llegada:" ) );
        JPanel panelAux = new JPanel( );
        panelAux.setLayout( new GridLayout( 1, 3 ) );

        cbAnio = new JComboBox( );
        cbAnio.addItem( "Año" );
        cbAnio.addItem( "2016" );
        cbAnio.addItem( "2017" );
        cbAnio.addItem( "2018" );
        cbAnio.addItem( "2019" );
        cbAnio.setSelectedItem( 0 );
        panelAux.add( cbAnio );

        cbMes = new JComboBox( );
        cbMes.setActionCommand( COMBO_MES );
        cbMes.addActionListener( this );
        cbMes.addItem( "Mes" );
        for( int i = 1; i <= 12; i++ )
        {
            cbMes.addItem( i + "" );
        }
        cbMes.setSelectedItem( 0 );
        panelAux.add( cbMes );

        cbDia = new JComboBox( );
        cbDia.addItem( "Día" );
        cbDia.setSelectedItem( 0 );
        panelAux.add( cbDia );
        panel.add( panelAux );

        panel.add( new JLabel( "Noches estadía:" ) );
        txtNochesEstadia = new JTextField( );
        panel.add( txtNochesEstadia );

        panel.add( new JLabel( "Adultos:" ) );
        cbCantidadAdultos = new JComboBox( );
        cbCantidadAdultos.addItem( 1 );
        cbCantidadAdultos.addItem( 2 );
        cbCantidadAdultos.addItem( 3 );
        cbCantidadAdultos.addItem( 4 );
        cbCantidadAdultos.addItem( 5 );
        cbCantidadAdultos.addItem( 6 );
        panel.add( cbCantidadAdultos );

        panel.add( new JLabel( "Niños:" ) );
        cbCantidadNinios = new JComboBox( );
        cbCantidadNinios.addItem( 0 );
        cbCantidadNinios.addItem( 1 );
        cbCantidadNinios.addItem( 2 );
        cbCantidadNinios.addItem( 3 );
        cbCantidadNinios.addItem( 4 );
        panel.add( cbCantidadNinios );

        panel.add( new JLabel( "Aerolínea:" ) );
        cbAerolinea = new JComboBox( );
        for( Aerolinea a : Aerolinea.values( ) )
        {
            cbAerolinea.addItem( a );
        }
        panel.add( cbAerolinea );

        panel.add( new JLabel( ) );
        btnGuardar = new JButton( GUARDAR );
        btnGuardar.setActionCommand( GUARDAR );
        btnGuardar.addActionListener( this );
        panel.add( btnGuardar );

        add( panel );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el hotel para reservarlo.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     */
    public void cambiarHotelSeleccionado( Hotel pHotel )
    {
        hotelSeleccionado = pHotel;
        txtHotelEscogido.setText( hotelSeleccionado.toString( ) );
    }

    /**
     * Manejo de los eventos de los combo box y los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( COMBO_MES ) )
        {
            try
            {
                String seleccionado = ( String )cbMes.getSelectedItem( );
                int mes = Integer.parseInt( seleccionado );
                if( mes == 2 )
                {
                    cbDia.removeAllItems( );
                    cbDia.addItem( "Día" );
                    for( int i = 1; i <= 28; i++ )
                    {
                        cbDia.addItem( i + "" );
                    }
                }
                else if( mes == 1 && mes == 3 && mes == 5 && mes == 7 && mes == 8 && mes == 10 && mes == 12 )
                {
                    cbDia.removeAllItems( );
                    cbDia.addItem( "Día" );
                    for( int i = 1; i <= 31; i++ )
                    {
                        cbDia.addItem( i + "" );
                    }
                }
                else
                {
                    cbDia.removeAllItems( );
                    cbDia.addItem( "Día" );
                    for( int i = 1; i <= 30; i++ )
                    {
                        cbDia.addItem( i + "" );
                    }
                }

            }
            catch( NumberFormatException pExcepcion )
            {
                // No hace nada
            }
        }
        else if( comando.equals( GUARDAR ) )
        {
            boolean puede = true;
            String mensajeError = "No se puede guardar las reservas: \n";
            int diaLlegada = 0;
            int mesLlegada = 0;
            int anioLlegada = 0;
            int diasEstadia = 1;
            try
            {
                diaLlegada = Integer.parseInt( ( String )cbDia.getSelectedItem( ) );
                mesLlegada = Integer.parseInt( ( String )cbMes.getSelectedItem( ) );
                anioLlegada = Integer.parseInt( ( String )cbAnio.getSelectedItem( ) );
            }
            catch( NumberFormatException e2 )
            {
                puede = false;
                mensajeError += "Debe seleccionar una fecha válida. \n";
            }
            try
            {
                diasEstadia = Integer.parseInt( txtNochesEstadia.getText( ) );
                if( diasEstadia <= 0 )
                {
                    puede = false;
                    mensajeError += "La cantidad de noches de estadía debe ser mayor a 0. \n";
                }
            }
            catch( Exception e2 )
            {
                puede = false;
                mensajeError += "Debe ingresar un número de noches de estadía válido. \n";
            }
            if( hotelSeleccionado == null )
            {
                puede = false;
                mensajeError += "Debe escoger un hotel para reservar.";
            }
            int cantidadAdultos = ( int )cbCantidadAdultos.getSelectedItem( );
            int cantidadNinios = ( int )cbCantidadNinios.getSelectedItem( );
            Aerolinea aerolinea = ( Aerolinea )cbAerolinea.getSelectedItem( );
            String cliente = txtCliente.getText( );
            if( cliente == null || cliente.equals( "" ) )
            {
                puede = false;
                mensajeError += "Debe ingresar un cliente válido. \n";
            }
            if( puede )
            {
                Calendar c = Calendar.getInstance( );
                c.set( anioLlegada, mesLlegada, diaLlegada );
                principal.guardarReserva( hotelSeleccionado, c.getTime( ),  cliente, cantidadAdultos, cantidadNinios, diasEstadia, aerolinea );
                this.dispose( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, mensajeError, "Guardar reserva", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( comando.equals( BUSCAR_HOTEL ) )
        {
            principal.iniciarDialogoSeleccionarHotel( this );
        }

    }

}