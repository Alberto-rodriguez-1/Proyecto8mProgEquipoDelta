import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; // Importación necesaria
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Proyecto8MTest {

    /* HU2 Test
       Verifica que la función prepararArchivos() crea correctamente 
       la carpeta y el fichero txt.
    */
    @Test
    void testArchivos() throws IOException {
        Proyecto8M.prepararArchivos();
        assertTrue(Files.exists(Paths.get("resultados")), "La carpeta no existe");
        assertTrue(Files.exists(Paths.get("resultados/resultados.txt")), "El archivo no existe");
    }
	
    /* HU1 Test
       Verifica que la función verificarDatos() se ejecute sin errores.
       Nota: Si faltan archivos en 'datos', este test fallará porque el programa se detiene.
    */
    @Test
    void testVerificarDatos() {
        assertDoesNotThrow(() -> Proyecto8M.verificarDatos());
    }
}