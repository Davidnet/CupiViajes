package uniandes.cupi2.cupiViajes.test;

import junit.framework.TestCase;
import org.junit.Before;
import uniandes.cupi2.cupiViajes.mundo.ComparadorHotelNombre;
import uniandes.cupi2.cupiViajes.mundo.Hotel;
import junit.framework.Test;

/**
 * Clase prara probar el correcto funcionamiento del comaprador de hoteles por nombre
 * @author alvar-go
 *
 */
public class ComparadorHotelNombreTest extends TestCase
{

    /**
     * Hotel para realizar la prueba
     */
    private Hotel hotel1;

    /**
     * Hotel para realizar la prueba
     */
    private Hotel hotel2;

    // REVIEW: WHAT ORIGINAL CODE private ComparadorHotelNombreTest comparador;
    private ComparadorHotelNombre comparador;



    /**
     * Escenario 1:Inicializa los hoteles en un estado conocido para poder realizar las pruebas
     * post: Se inicializan los hoteles con valores conocidos y el comparador
     */
    @Before
    public void setUp()
    {
        // REVIEW: Parte 1 Punto 1a completar según la documentación
        hotel1 = new Hotel("Hotel las Américas", "Cartagena", 4, 150000, "/data/imagenes/americas.jpg");
        hotel2 = new Hotel(" Hilton Garden Inn", "Orlando", 4, 260000, "/data/imagenes/hilton.jpg");
    }

    /**
     * Prueba 1: Se encarga de verificar el método compare del comparador de hoteles.<br>
     * <b> Métodos a probar: </b> <br>
     * compare<br>
     * <b> Casos de prueba:</b><br>
     * 1. El hotel1 es igual al hotel1.<br>
     * 2. El hotel1 es menor al hotel2.<br>
     * 3. El hotel2 es mayor al hotel1.
     */
    public void testCompare()
    {
        // REVIEW Parte 1 Punto 1b completar según la documentación
        comparador = new ComparadorHotelNombre();
        assertEquals("Hoteles iguales no son iguales",0, comparador.compare(hotel1, hotel2));
        assertEquals("Hotel 1 debería ser menor a Hotel2", 1, comparador.compare(hotel2, hotel1));
        assertEquals("Hotel 2 debería ser mayor a Hotel 1 ", -1, comparador.compare(hotel1,hotel2));


    }
}
