import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Proyecto8MTest {
    @Test
    public void testConstructor(){
			Programa programa =new Programa();
			assertNotEquals(null, programa);
	}
    /**  HU2 Test
       Verifica que la función prepararArchivos() crea correctamente 
       la carpeta y el fichero txt.
    */
    @Test
    void testArchivos() throws IOException {
        Programa.prepararArchivos();
        assertTrue(Files.exists(Paths.get("resultados")), "La carpeta no existe");
        assertTrue(Files.exists(Paths.get("resultados/resultados.txt")), "El archivo no existe");
    }

    /**
     * HU1 Test
     * Verifica que la función verificarDatos() se ejecute sin errores.
     * Nota: Si faltan archivos en 'datos', este test fallará porque el programa se
     * detiene.
     */
    @Test
    void testVerificarDatos() {
        assertDoesNotThrow(() -> Programa.verificarDatos());
    }

    /**
     * HU4 Test
     * Verifica que el método leerRealidades() devuelve una lista válida
     * y que no es null.
     * Esto confirma que el archivo JSON se ha leído correctamente.
     */
    @Test
    void testLeerRealidadesNoNull() throws IOException {
        List<Realidad> realidades = Programa.leerRealidades();
        assertNotNull(realidades);
    }

    @Test
    void testLeerRealidadesNoVacia() throws IOException {
        List<Realidad> realidades = Programa.leerRealidades();
        assertTrue(realidades.size() > 0);
    }

    @Test
    void testNumeroRealidades() throws IOException {
        List<Realidad> realidades = Programa.leerRealidades();
        assertEquals(10, realidades.size());
    }
    /** 
     * H3 test
     * Verifica que la funcion leer memes lea el fichero y genere un lista
     */
    @Test public void testLeerMeme() throws IOException { 
        List<String> memes = Programa.leerMeme(); 
        // Comprobamos que la lista no es null 
        assertNotNull(memes); 
        // Comprobamos que tiene elementos 
        assertTrue(memes.size() > 0); 
        // Comprobamos que el primer meme no es vacío 
        assertFalse(memes.get(0).isEmpty());
        }
}