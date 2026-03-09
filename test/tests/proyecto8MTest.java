import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class proyecto8MTest {

    @Test
    void testArchivos() throws IOException {
        proyecto8M.prepararArchivos();
        assertTrue(Files.exists(Paths.get("resultados")), "La carpeta no existe");
        assertTrue(Files.exists(Paths.get("resultados/resultados.txt")), "El archivo no existe");
    }
}