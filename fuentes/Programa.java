import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.StandardOpenOption;

/**
 * @author: "Nicanor Gil", "Alberto Rodriguez", "Mario Carmona"
 */ 

public class Programa {

    public static void main(String[] args) throws IOException {
        verificarDatos();
        prepararArchivos();

        // HU4 - Cargamos las realidades y las mostramos por pantalla
        List<Realidad> realidades = leerRealidades();
        System.out.println("Realidades cargadas: " + realidades.size());
        for (Realidad r : realidades) {
            System.out.println(r);
        }
    }

    /*
     * HU1 - Verificación de archivos
     * Comprobamos que existan los archivos necesarios en la carpeta datos.
     * Si falta alguno, el programa se cierra para evitar errores.
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
     * HU2 - Preparación de directorios
     * Creamos la carpeta 'resultados' y el archivo 'resultados.txt' si no existen.
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
     * Lee el archivo "realidades.json" ubicado en la carpeta datos y
     * crea una lista de objetos Realidad con la información obtenida.
     * 
     * El método recorre cada línea del archivo JSON y extrae los valores
     * correspondientes a id, texto y fuente. Cuando se han leído los tres
     * atributos, se crea un objeto Realidad que se añade a la lista.
     * 
     * @return una lista de objetos Realidad cargados desde el archivo JSON
     * 
     * @throws IOException si ocurre un error al leer el archivo
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
                // Filtramos la línea para quedarnos solo con el número del ID
                id = Integer.parseInt(linea.replaceAll("[^0-9]", ""));

            } else if (linea.startsWith("\"texto\"")) {
                // Extraemos el contenido del campo texto entre comillas
                texto = linea.substring(linea.indexOf(":") + 3, linea.lastIndexOf("\""));

            } else if (linea.startsWith("\"fuente\"")) {
                // Extraemos la fuente y añadimos el objeto nuevo a la lista
                fuente = linea.substring(linea.indexOf(":") + 3, linea.lastIndexOf("\""));
                realidades.add(new Realidad(id, texto, fuente));
            }
        }

        return realidades;
    }

    // Método para leer memes (pendiente de implementar)
    public static List<String> leerMeme() {
        return new ArrayList<>();
    }

    /* HU9
     * Gestión de puntuaciones y ranking.
     * Si la puntuación entra en el top 3, se pide el nombre y se guarda
     * en el fichero mejores.txt usando APPEND para añadir al final.
     */
    public static void gestionarPuntuacion(Integer puntuacionUsuario, Scanner scanner, List<String> mejores) throws IOException {
        // Miramos si la puntuación es lo suficientemente alta para entrar en el top
        Boolean esMejor = mejores.size() < 3 || puntuacionUsuario > Integer.parseInt(mejores.get(mejores.size() - 1).split(";")[1]);

        if (esMejor) {
            // Pillamos el nombre del usuario por consola
            String nombre = scanner.nextLine().trim();
            // Lo añadimos a nuestra lista en memoria
            mejores.add(nombre + ";" + puntuacionUsuario);

            // Mantenemos solo los 3 mejores
            if (mejores.size() > 3) 
                mejores.remove(mejores.size() - 1);

            // Guardamos la lista actualizada en el disco
            Files.write(Paths.get("resultados/mejores.txt"), mejores, 
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }
}