import java.io.IOException;
import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author: "Nicanor Gil", "Alberto Rodriguez", "Mario Carmona"
 */

public class MemesMujeres {

    public static void main(String[] args) throws IOException {

        /**
         * TT11
         * Menú con TRY CATCH por si alguien pone un caracter que no es un Integer
         * Implementado con SWITCH.
         * Usamos tambien un Do While porque necesitamos crear un bucle que no sabemos
         * cuando va a acabar.
         */
        Scanner teclado = new Scanner(System.in);
        Integer opcion = 0;
        do {
            Integer puntuacion = 0;
            System.out.println("\n----------------------------------");
            System.out.println("      BIENVENIDO A 8M MEMES.        ");
            System.out.println("-----------------------------------");
            System.out.println("1. Iniciar carga de memes.");
            System.out.println("2. Salir.");
            System.out.println("3. Mostrar fuentes.");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException error) {
                opcion = 0; // Opción invalida si no es un número
            }

            switch (opcion) {
                case 1:
                    for (int i = 0; i < 5; i++) {
                        System.out.println("\nEjecutando procesos...");
                        verificarDatos();
                        prepararArchivos();
                        try {
                            Solucion solucion = mostrarMemes();
                            System.out.print("Seleccione el numero de una de estas realidades:");
                            Integer respuesta = Integer.parseInt(teclado.nextLine());

                            if (solucion.getId().equals(respuesta)) {
                                System.out.println("Correcto");
                                puntuacion++;
                            } else {
                                System.out.println("Incorrecto");
                            }

                            // HU7
                            System.out.println("Puntuación actual: " + puntuacion + "/" + (i + 1));

                        } catch (NumberFormatException e) {
                            System.out.println("Error: debes introducir un número válido.");
                        } catch (Exception e) {
                            System.out.println("Error al mostrar memes: " + e.getMessage());
                        }
                    }
                    // List<String> mejores=leerPuntuaciones();
                    // gestionarPuntuacion( puntuacion, teclado, mejores);
                    opcion = 2;
                case 2:
                    mostrarRanking();
                    System.out.println("Saliendo del programa...");
                    break;
                case 3:
                    mostrarFuentes();
                default: // Por si alguna opcion no es la esperada
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 2);

        teclado.close();
    }

    /**
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

    /**
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

    /**
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

    /**
     * Esta funcion lee el fichero de resultados para crear una lista de
     * puntuaciones
     */
    /*
     * public static void leerPuntuaciones(){
     * 
     * }
     */
    /**
     * Muestra un meme aleatorio junto con una lista de todas las realidades
     * numeradas
     * 
     * @throws Exception es una excepcion desconocida
     *
     */
    public static Solucion mostrarMemes() throws Exception {
        Random random = new Random();
        // HU5 - Mostramos por pantalla 1 meme y todas las realidades
        List<Realidad> realidades = leerRealidades();
        List<Meme> memes = leerMeme();
        Meme meme = memes.get(random.nextInt(memes.size()));
        System.out.print("Selecciona la realidad que desmiente este meme:");
        System.out.println(meme.getTexto());
        for (Realidad realidad : realidades) {
            System.out.println(realidad.getId() + ":" + realidad.getTexto());
        }
        List<Solucion> soluciones = leerSoluciones();
        for (Solucion solucion : soluciones) {
            if (solucion.getId() == meme.getId()) {
                return solucion;
            }
        }
        return null;
    }

    /**
     * Muestra las fuentes de las realidades para evitar grandes cantidades de texto
     * en el funcionamiento del programa
     * 
     * @throws Exception es una excepcion desconocida
     */
    public static void mostrarFuentes() throws IOException {
        List<Realidad> realidades = leerRealidades();
        for (Realidad realidad : realidades) {
            System.out.println(realidad.getId() + ":" + realidad.getTexto() + "Fuente:" + realidad.getFuente());
        }
    }

    /**
     * Lee los memes de un fichero
     * 
     * @return Una lista de Memes
     * @throws Exception es una excepcion desconocida
     */
    public static List<Meme> leerMeme() throws IOException {

        List<Meme> resultado = new ArrayList<>();

        Path path = Paths.get("datos", "memes.txt");

        // Método para leer memes (pendiente de implementar)
        List<String> datos = Files.readAllLines(path);
        Integer id = 0;
        String[] trozos = datos.get(0).split(";");
        for (String tmeme : trozos) {
            Meme meme = new Meme(tmeme, id);
            id++;
            resultado.add(meme);
        }

        return resultado;
    }

    /**
     * TT12
     * Leer fichero de soluciones
     * 
     * @return una lista de Soluciones
     * @throws Exception es una excepcion desconocida
     */
    public static List<Solucion> leerSoluciones() throws Exception {
        List<Solucion> resultado = new ArrayList<>();
        File ficheroXML = new File("datos", "soluciones.xml");
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = factoria.newDocumentBuilder();
        Document documento = constructor.parse(ficheroXML);

        Element raiz = documento.getDocumentElement();

        NodeList listaSoluciones = raiz.getElementsByTagName("solucion");

        for (int i = 0; i < listaSoluciones.getLength(); i++) {
            Element solucion = (Element) listaSoluciones.item(i);
            Integer id = Integer.valueOf(solucion.getAttribute("id"));
            String meme = solucion.getElementsByTagName("meme").item(0).getTextContent();
            String realidad = solucion.getElementsByTagName("realidad").item(0).getTextContent();
            Solucion solucionFinal = new Solucion(id, meme, realidad);
            resultado.add(solucionFinal);
        }
        return resultado;
    }

    /**
     * HU9
     * Gestión de puntuaciones y ranking.
     * Si la puntuación entra en el top 3, se pide el nombre y se guarda
     * en el fichero mejores.txt usando APPEND para añadir al final.
     */
    public static void gestionarPuntuacion(Integer puntuacionUsuario, Scanner scanner, List<String> mejores)
            throws IOException {
        // Miramos si la puntuación es lo suficientemente alta para entrar en el top
        Boolean esMejor = mejores.size() < 3
                || puntuacionUsuario > Integer.parseInt(mejores.get(mejores.size() - 1).split(";")[1]);

        if (esMejor) {
            // Pillamos el nombre del usuario por consola
            System.out.print("Felicidades has entrado en el top. Introduce un nombre para ser registrado:");
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

    /**
     * HU10 - Mostrar mejores puntuaciones y despedida.
     * Lee el archivo 'mejores.txt' en la carpeta resultados y muestra
     * el ranking de los usuarios con sus puntos.
     */
    public static void mostrarRanking() throws IOException {
        Path rutaRanking = Paths.get("resultados/mejores.txt");

        System.out.println("\n--- RANKING DE MEJORES PUNTUACIONES ---");

        if (Files.exists(rutaRanking)) {
            // Leemos todas las líneas del archivo de mejores puntuaciones
            List<String> lineas = Files.readAllLines(rutaRanking);

            if (lineas.isEmpty()) {
                System.out.println("Aún no hay puntuaciones registradas.");
            } else {
                // Recorremos la lista y mostramos cada nombre y puntuación
                for (String linea : lineas) {
                    // Separamos el nombre del puntaje
                    String[] datos = linea.split(";");
                    System.out.println("Jugador: " + datos[0] + " - Puntos: " + datos[1]);
                }
            }
        } else {
            System.out.println("No se encontró el archivo de ranking.");
        }

        // Despedida del sistema
        System.out.println("¡Gracias por jugar! Hasta la próxima.");
    }
}