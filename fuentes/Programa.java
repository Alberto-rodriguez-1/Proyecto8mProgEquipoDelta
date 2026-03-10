import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Programa {

    public static void main(String[] args) throws IOException {
        // Ejecución de las validaciones iniciales
        verificarDatos();
        prepararArchivos();
    }

    /*
     * HU1
     * Comprueba que existen los archivos memes.txt, realidades.json y
     * soluciones.xml
     * dentro de la carpeta 'datos'. Si falta alguno, detiene el programa.
     */
    public static void verificarDatos() {
        if (!Files.exists(Paths.get("datos/memes.txt")) ||
                !Files.exists(Paths.get("datos/realidades.json")) ||
                !Files.exists(Paths.get("datos/soluciones.xml"))) {

            System.out.println("Faltan archivos en datos.");
            System.exit(0);
        }
    }

    /*
     * HU2
     * Comprueba que existe el directorio 'resultados' y el fichero
     * 'resultados.txt'.
     * Si no existen, los crea automáticamente.
     */
    public static void prepararArchivos() throws IOException {
        Path directorio = Paths.get("resultados");
        // Usamos resolve para que el fichero siempre esté DENTRO del directorio
        Path fichero = directorio.resolve("resultados.txt");

        if (Files.notExists(directorio)) {
            Files.createDirectory(directorio);
            System.out.println("No estaba creado el directorio resultados.");
            System.out.println("Directorio resultados creado.");
        } else {
            System.out.println("Directorio ya está creado.");
        }

        if (Files.notExists(fichero)) {
            Files.createFile(fichero);
            System.out.println("No estaba creado el fichero resultados.txt.");
            System.out.println("Fichero resultados.txt creado.");
        } else {
            System.out.println("Fichero ya está creado.");
        }
    }

    public static List<String> leerMeme() {

    }
}