import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Proyecto8MTest {
    @Test
    public void testConstructor(){
			Programa programa =new Programa();
			assertNotEquals(null, programa);
	}
    /* HU2 Test
       Verifica que la función prepararArchivos() crea correctamente 
       la carpeta y el fichero txt.
    */
    @Test
    void testArchivos() throws IOException {
        Programa.prepararArchivos();
        assertTrue(Files.exists(Paths.get("resultados")), "La carpeta no existe");
        assertTrue(Files.exists(Paths.get("resultados/resultados.txt")), "El archivo no existe");
    }
	
    /* HU1 Test
       Verifica que la función verificarDatos() se ejecute sin errores.
       Nota: Si faltan archivos en 'datos', este test fallará porque el programa se detiene.
    */
    @Test
    void testVerificarDatos() {
        assertDoesNotThrow(() -> Programa.verificarDatos());
    }
}