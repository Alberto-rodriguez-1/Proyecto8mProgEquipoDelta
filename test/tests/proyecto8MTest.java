import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class proyecto8MTest {

    @BeforeEach
    void inicio() throws IOException {
        // Configuramos el entorno de datos para los tests (HU1)
        Path datosDir = Paths.get("datos");
        if (Files.notExists(datosDir)) Files.createDirectory(datosDir);
        
        Files.writeString(datosDir.resolve("memes.txt"), "Contenido de prueba");
        // JSON de prueba con una realidad para el test de lectura (HU4)
        String jsonPrueba = "\"id\": 1\n\"texto\": \"Dato Real\"\n\"fuente\": \"Libro A\"";
        Files.writeString(datosDir.resolve("realidades.json"), jsonPrueba);
        Files.writeString(datosDir.resolve("soluciones.xml"), "<xml></xml>");

        // Limpieza de la carpeta de resultados antes de cada test
        Path resDir = Paths.get("resultados");
        if (Files.exists(resDir)) {
            Files.deleteIfExists(resDir.resolve("resultados.txt"));
            Files.deleteIfExists(resDir.resolve("mejores.txt"));
        }
    }

    @Test
    void testHU1_VerificarDatos() {
        // Comprueba que no salta el System.exit(0) si los archivos existen
        assertDoesNotThrow(() -> Programa.verificarDatos());
    }

    @Test
    void testHU2_PrepararArchivos() throws IOException {
        // Comprueba que se crean la carpeta y el txt correctamente
        Programa.prepararArchivos();
        assertTrue(Files.exists(Paths.get("resultados")), "La carpeta resultados debería existir");
        assertTrue(Files.exists(Paths.get("resultados/resultados.txt")), "El archivo resultados.txt debería existir");
    }

    @Test
    void testHU4_LeerRealidades() throws IOException {
        // Verifica que la lectura del JSON funciona y devuelve objetos
        List<Realidad> lista = Programa.leerRealidades();
        assertNotNull(lista, "La lista no debería ser null");
        assertFalse(lista.isEmpty(), "La lista debería tener contenido");
    }

    @Test
    void testHU9_GestionarPuntuacion() throws IOException {
        // Simulamos una puntuación alta y un nombre de usuario
        List<String> mejores = new ArrayList<>();
        mejores.add("Ana;9");
        Scanner scannerSimulado = new Scanner("Carlos\n");

        Programa.prepararArchivos();
        Programa.gestionarPuntuacion(10, scannerSimulado, mejores);

        // Verificamos que se ha añadido a la lista y se ha guardado en disco
        assertTrue(mejores.contains("Carlos;10"), "Carlos debería estar en la lista");
        assertTrue(Files.exists(Paths.get("resultados/mejores.txt")), "El archivo de mejores debería haberse creado");
    }

    /**
     * Test para la HU10
     * Verifica que el método mostrarRanking() funcione bien.
     */
    @Test
    void testHU10_MostrarRanking() throws IOException {
        // Caso 1: Probamos qué pasa si el archivo todavía no existe
        assertDoesNotThrow(() -> Programa.mostrarRanking());

        // Caso 2: Creamos unos datos de prueba para ver si los muestra
        Programa.prepararArchivos();
        List<String> datosPrueba = List.of("Nicanor;10", "Mario;8");
        Files.write(Paths.get("resultados/mejores.txt"), datosPrueba);

        // Comprobamos que el método lee esos datos de prueba sin dar errores
        assertDoesNotThrow(() -> Programa.mostrarRanking(), "Debería imprimir el ranking por consola");
    }
}