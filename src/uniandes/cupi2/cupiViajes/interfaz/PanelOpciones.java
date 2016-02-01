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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel con las opciones de búsqueda y botones de extensión.
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando de buscar reserva.
     */
    private final static String BUSCAR = "Buscar reserva";

    /**
     * Constante que representa el comando de opción 1.
     */
    private final static String OPCION_1 = "Opción 1";

    /**
     * Constante que representa el comando de opción 2.
     */
    private final static String OPCION_2 = "Opción 2";

    /**
     * Comando que representa la opción de búsqueda por ciudad.
     */
    public final static String CIUDAD = "Ciudad";

    /**
     * Comando que representa la opción de búsqueda por cliente.
     */
    public final static String CLIENTE = "Cliente";

    /**
     * Comando que representa la opción de búsqueda por aerolínea.
     */
    public final static String AEROLINEA = "Aerolínea";

    /**
     * Comando que representa la opción de búsqueda por mayor cantidad de personas en el viaje.
     */
    public final static String MAYOR_PERSONAS = "Mayor cantidad de personas";

    /**
     * Comando que representa la opción de búsqueda por menor cantidad de personas en el viajes.
     */
    public final static String MENOR_PERSONAS = "Menor cantidad de personas";

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
     * Botón buscar reserva.
     */
    private JButton btnBuscar;

    /**
     * Botón opción 1.
     */
    private JButton btnOpcion1;

    /**
     * Botón opción 2.
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel opciones.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelOpciones( InterfazCupiViajes pPrincipal )
    {
        setLayout( new GridLayout( 1, 3 ) );
        setBorder( new TitledBorder( "Opciones" ) );
        setPreferredSize( new Dimension( 700, 50 ) );

        principal = pPrincipal;

        btnBuscar = new JButton( BUSCAR );
        btnBuscar.setActionCommand( BUSCAR );
        btnBuscar.addActionListener( this );
        add( btnBuscar );

        btnOpcion1 = new JButton( OPCION_1 );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        btnOpcion2 = new JButton( OPCION_2 );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( OPCION_1 ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( comando.equals( OPCION_2 ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( comando.equals( BUSCAR ) )
        {
            String[] opciones = { CLIENTE, CIUDAD, AEROLINEA, MAYOR_PERSONAS, MENOR_PERSONAS };
            JComboBox comboOpciones = new JComboBox( opciones );
            JOptionPane.showMessageDialog( null, comboOpciones, "¿Por qué criterio desea buscar la reserva?", JOptionPane.QUESTION_MESSAGE );
            String opcion = ( String )comboOpciones.getSelectedItem( );
            if( opcion != null )
            {
                principal.buscarReserva( opcion );
            }
        }
    }

}
