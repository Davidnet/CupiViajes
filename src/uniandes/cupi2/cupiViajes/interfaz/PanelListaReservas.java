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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;

/**
 * Panel con la lista de reservas guardadas.
 */
public class PanelListaReservas extends JPanel implements ListSelectionListener, ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para ordenar la lista de reservas.
     */
    private final static String ORDENAR = "Ordenar lista";

    /**
     * Constante que representa el comando para agregar una nueva reserva.
     */
    private final static String NUEVO = "Nueva reserva";



    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiViajes principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Lista de las reservas.
     */
    //Review Parte 6 Punto 1a Declare el atributo para mostrar la lista de reservas llamado listaReservas
    private JList listaReservas;


    /**
     * Panel con un scroll que contiene la lista de reservas.
     */
    private JScrollPane scrollReservas;

    /**
     * Botón para ordenar la lista.
     */
    private JButton btnOrdenar;

    /**
     * Botón para agregar una nueva reserva.
     */
    private JButton btnNuevo;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con la lista de reservas. <br>
     * <b> post: </b> Todos los elementos gráficos fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelListaReservas( InterfazCupiViajes pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Reservas" ) );
        setPreferredSize( new Dimension( 250, 0 ) );

        // Review Parte 6 Punto 1b Inicialice la lista de reservas. Tenga en cuenta que:
        //      Solo se puede seleccionar una reserva a la vez
        //      El panel responsable de actuar cuando se seleccione un elemento de la lista es este panel
        listaReservas = new JList();
        listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaReservas.addListSelectionListener(this);


        scrollReservas = new JScrollPane( listaReservas );
        scrollReservas.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollReservas.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollReservas.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new LineBorder( Color.BLACK, 1 ) ) );

        add( scrollReservas, BorderLayout.CENTER );

        JPanel panelAux = new JPanel( );
        panelAux.setLayout( new GridLayout( 2, 1 ) );

        btnOrdenar = new JButton( ORDENAR );
        btnOrdenar.setActionCommand( ORDENAR );
        btnOrdenar.addActionListener( this );
        panelAux.add( btnOrdenar );

        btnNuevo = new JButton( NUEVO );
        btnNuevo.setActionCommand( NUEVO );
        btnNuevo.addActionListener( this );
        panelAux.add( btnNuevo );

        add( panelAux, BorderLayout.SOUTH );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de las reservas dada un ArrayList.
     * @param pReservas La lista de reservas a mostrar. pReservas != null.
     */
    public void actualizarLista( ArrayList<ReservaViaje> pReservas )
    {
        //// TODO: 31/01/2016  
        //REVIEW Parte 6 Punto 1c elimine todos los elementos de la lista de reservas
        //REVIEW Parte 6 Punto 1d Actualice la lista de reservas con el ArrayList que llega por parámetro
        listaReservas.removeAll( );
        listaReservas.setListData( pReservas.toArray( ) );
    }

    /**
     * Selecciona la reserva que se está visualizando.
     * @param pReserva Reserva que se quiere visualizar. pReserva != null.
     */
    public void seleccionarReserva( ReservaViaje pReserva )
    {
        listaReservas.setSelectedValue( pReserva, true );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( NUEVO ) )
        {
            principal.iniciarDialogoGuardarReserva( );
        }
        else if( comando.equals( ORDENAR ) )
        {
            principal.ordenarLista(  );

        }
    }

    /**
     * Atiende el evento cuando el usuario selecciona una reserva de la lista.
     * @param pEvento Evento de selección de un elemento de la lista de reservas. pEvento != null.
     */
    public void valueChanged( ListSelectionEvent pEvento )
    {
        if( listaReservas.getSelectedValue( ) != null )
        {
            ReservaViaje reserva = ( ReservaViaje )listaReservas.getSelectedValue( );
            principal.actualizarInformacion( reserva, reserva.darHotel( ) );
        }

    }

}
