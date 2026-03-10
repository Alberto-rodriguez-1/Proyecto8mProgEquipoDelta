import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Programa {

    public static void main(String[] args) throws IOException {
        verificarDatos();
        prepararArchivos();

        // HU4 - Leer realidades
        List<Realidad> realidades = leerRealidades();
        System.out.println("Realidades cargadas: " + realidades.size());
        for (Realidad r : realidades) {
            System.out.println(r);
        }
    }

    /*
     * HU1
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
     */
    public static void prepararArchivos() throws IOException {
        Path directorio = Paths.get("resultados");
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

    /*
     * HU4
     */
    public static List<Realidad> leerRealidades() throws IOException {
        List<Realidad> realidades = new ArrayList<>();
        List<String> lineas = Files.readAllLines(Paths.get("datos/realidades.json"));

        int id = 0;
        String texto = "";
        String fuente = "";

        for (String linea : lineas) {
            linea = linea.trim();

            if (linea.startsWith("\"id\"")) {
                id = Integer.parseInt(linea.replaceAll("[^0-9]", ""));

            } else if (linea.startsWith("\"texto\"")) {
                texto = linea.substring(linea.indexOf(":") + 3, linea.lastIndexOf("\""));

            } else if (linea.startsWith("\"fuente\"")) {
                fuente = linea.substring(linea.indexOf(":") + 3, linea.lastIndexOf("\""));
                realidades.add(new Realidad(id, texto, fuente));
            }
        }

        return realidades;
    }

    public static List<String> leerMeme() {
        return new ArrayList<>();
    }
}