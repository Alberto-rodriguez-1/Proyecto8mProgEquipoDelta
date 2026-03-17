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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Clase de pruebas unitarias para el Proyecto 8M.
 * Esta clase valida el cumplimiento de las Historias de Usuario (HU)
 * relacionadas
 * con la gestión de archivos, carga de datos JSON/TXT y el sistema de ranking.
 */
public class ProgramaTest {

    /**
     * Configuración del entorno de prueba antes de cada ejecución.
     * Prepara la carpeta 'datos' con archivos temporales y limpia la carpeta
     * 'resultados'.
     */
    @BeforeEach
    void inicio() throws IOException {
        // --- Configuración de datos de entrada (HU1, HU3, HU4) ---
        Path datosDir = Paths.get("datos");
        if (Files.notExists(datosDir)) {
            Files.createDirectory(datosDir);
        }

        // Archivo para HU3
        Path memes = datosDir.resolve("memes.txt");
        if (Files.notExists(memes)) {
            Files.writeString(memes, "Meme de prueba 1; Meme de prueba 2");
        }

        // Archivo para HU4 (Simulación de JSON)
        Path realidades = datosDir.resolve("realidades.json");
        if (Files.notExists(realidades)) {
            String jsonPrueba = "\"id\": 1\n\"texto\": \"Dato Real de Prueba\"\n\"fuente\": \"Libro A\"";
            Files.writeString(realidades, jsonPrueba);
        }

        // Archivo para HU1
        Path soluciones = datosDir.resolve("soluciones.xml");
        if (Files.notExists(soluciones)) {
            Files.writeString(soluciones, "<xml></xml>");
        }

        // --- Limpieza de entorno de salida (HU2, HU9, HU10) ---
        Path resDir = Paths.get("resultados");
        if (Files.exists(resDir)) {
            Files.deleteIfExists(resDir.resolve("resultados.txt"));
            Files.deleteIfExists(resDir.resolve("mejores.txt"));
        }
    }

    /**
     * Test técnico: Verifica que la clase Programa sea instanciable.
     */
    @Test
    public void testConstructor() {
        Programa programa = new Programa();
        assertNotEquals(null, programa, "La instancia de Programa no debería ser nula.");
    }

    // =========================================================================
    // TESTS DE HISTORIAS DE USUARIO (HU)
    // =========================================================================

    /**
     * HU1: Verificación de archivos de datos.
     * Comprueba que el sistema detecta correctamente la presencia de los archivos
     * necesarios y no interrumpe la ejecución si están presentes.
     */
    @Test
    void testHU1_VerificarDatos() {
        assertDoesNotThrow(() -> Programa.verificarDatos(),
                "El método no debería lanzar excepciones ni cerrar el programa si los archivos existen.");
    }

    /**
     * HU2: Preparación de archivos de salida.
     * Verifica la creación automática del directorio 'resultados' y el fichero
     * 'resultados.txt'.
     */
    @Test
    void testHU2_PrepararArchivos() throws IOException {
        Programa.prepararArchivos();
        assertTrue(Files.exists(Paths.get("resultados")), "La carpeta 'resultados' debería haberse creado.");
        assertTrue(Files.exists(Paths.get("resultados/resultados.txt")),
                "El archivo 'resultados.txt' debería existir.");
    }

    /**
     * HU3: Carga de memes.
     * Valida que el archivo de memes se lea y se fragmente correctamente en una
     * lista.
     */
    @Test
    public void testHU3_LeerMeme() throws IOException {
        List<Meme> memes = Programa.leerMeme();
        assertNotNull(memes, "La lista de memes no debe ser nula.");
        assertTrue(memes.size() > 0, "La lista de memes debería tener elementos.");
    }

    /**
     * HU4: Carga de realidades desde JSON.
     * Verifica el parseo manual de 'realidades.json' y la creación de objetos
     * Realidad.
     */
    @Test
    void testHU4_LeerRealidades() throws IOException {
        List<Realidad> lista = Programa.leerRealidades();
        assertNotNull(lista, "La lista de realidades no debería ser nula.");
        assertFalse(lista.isEmpty(), "La lista debería contener al menos una realidad.");
        assertEquals(0, lista.get(0).getId(), "El ID de la primera realidad debe ser 0.");
    }

    /**
     * HU9: Gestión de puntuación y persistencia.
     * Simula la entrada de un nuevo jugador y comprueba que se guarda en el
     * ranking.
     */
    @Test
    void testHU9_GestionarPuntuacion() throws IOException {
        List<String> mejores = new ArrayList<>();
        mejores.add("Ana;9");
        Scanner scannerSimulado = new Scanner("Carlos\n"); // Simula entrada de usuario

        Programa.prepararArchivos();
        Programa.gestionarPuntuacion(10, scannerSimulado, mejores);

        assertTrue(mejores.contains("Carlos;10"), "La lista debería incluir el nuevo récord de Carlos.");
        assertTrue(Files.exists(Paths.get("resultados/mejores.txt")),
                "El archivo 'mejores.txt' debería haberse actualizado.");
    }

    /**
     * HU10: Visualización del Ranking.
     * Verifica que el método mostrarRanking() se ejecute sin errores tanto si hay
     * datos como si no.
     */
    @Test
    void testHU10_MostrarRanking() throws IOException {
        // Escenario A: Archivo inexistente
        assertDoesNotThrow(() -> Programa.mostrarRanking());

        // Escenario B: Archivo con datos
        Programa.prepararArchivos();
        List<String> datosPrueba = List.of("Nicanor;10", "Mario;8");
        Files.write(Paths.get("resultados/mejores.txt"), datosPrueba);

        assertDoesNotThrow(() -> Programa.mostrarRanking(), "Debería imprimir el ranking sin lanzar excepciones.");
    }

    @Test
    public void testMostrarMemes() {

        try {
            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            System.setOut(new PrintStream(salida));
            Programa.mostrarMemes();
            String resultado = salida.toString();
            assertTrue(resultado.contains("Selecciona la realidad que desmiente este meme:"));
            assertFalse(resultado.isEmpty());
        } catch (Exception e) {
            fail("Se produjo una IOException: " + e.getMessage());
        }
    }

    @Test
    public void testMostrarFuentes() {
        try {
            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            System.setOut(new PrintStream(salida));
            Programa.mostrarFuentes();
            List<Realidad> realidades = Programa.leerRealidades();
            String resultado = salida.toString();
            assertTrue(resultado.contains("Fuente:"));
            assertFalse(resultado.isEmpty());
        } catch (Exception e) {
            fail("Se produjo una IOException: " + e.getMessage());
        }
    }
}