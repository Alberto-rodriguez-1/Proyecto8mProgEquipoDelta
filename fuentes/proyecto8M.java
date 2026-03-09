import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class proyecto8M {

    public static void main(String[] args) throws IOException {
        prepararArchivos();
    }

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
}