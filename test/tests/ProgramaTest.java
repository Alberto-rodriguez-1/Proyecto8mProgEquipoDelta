import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramaTest {

    @BeforeEach
    void inicio() throws IOException {
        // HU1: Necesitamos que existan los archivos para que verificarDatos() no mate el test
        Path datosDir = Paths.get("datos");
        if (Files.notExists(datosDir)) Files.createDirectory(datosDir);
        
        Files.writeString(datosDir.resolve("memes.txt"), "dummy content");
        Files.writeString(datosDir.resolve("realidades.json"), "[]");
        Files.writeString(datosDir.resolve("soluciones.xml"), "<xml></xml>");

        // Limpieza de resultados
        Path resDir = Paths.get("resultados");
        if (Files.exists(resDir)) {
            Files.deleteIfExists(resDir.resolve("resultados.txt"));
            Files.deleteIfExists(resDir.resolve("mejores.txt"));
        }
    }

    @Test
    void testArchivos() throws IOException {
        Programa.prepararArchivos();
        assertTrue(Files.exists(Paths.get("resultados")));
    }

    @Test
    void testVerificarDatos() {
        // Si los archivos creados en constructor() están ahí, esto no lanzará error
        assertDoesNotThrow(() -> Programa.verificarDatos());
    }
@Test
void testGestionarPuntuacion() throws IOException {
    List<String> mejores = new ArrayList<>();
    mejores.add("Ana;9");
    mejores.add("Juan;7");

    Scanner scanner = new Scanner("Carlos\n");

    Programa.prepararArchivos(); // Esto crea la carpeta 'resultados'
    Path pathMejores = Paths.get("resultados/mejores.txt");
    if (Files.notExists(pathMejores)) {
        Files.createFile(pathMejores); // Creamos el archivo vacío para que APPEND no falle
    }

    // 4. Ejecutar la lógica
    Programa.gestionarPuntuacion(8, scanner, mejores);

    // 5. Verificaciones
    assertEquals(3, mejores.size(), "La lista debería tener 3 elementos");
    assertTrue(mejores.contains("Carlos;8"), "Carlos debería estar en la lista");
}
}