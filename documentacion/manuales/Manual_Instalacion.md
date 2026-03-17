# TD1 - Manual de Instalación

Este documento detalla los requisitos y los procedimientos necesarios para desplegar, compilar y ejecutar la aplicación del **Proyecto 8M: Desmintiendo Bulos**.

---

## 1. Requerimientos del Sistema
Para garantizar el correcto funcionamiento del software, el sistema debe cumplir con lo siguiente:

* **Entorno de ejecución:** Java Development Kit (JDK) versión 17 o superior.
* **Gestor de versiones:** Git, para la descarga del código fuente.
* **Dependencias:** Librería JUnit 5 (`junit-platform-console-standalone-1.9.2.jar`) para la validación de pruebas unitarias.
* **Estructura de datos:** El sistema requiere la presencia de la carpeta `datos/` en la raíz con los ficheros `memes.txt`, `realidades.json` y `soluciones.xml`.



## 2. Descarga del Repositorio
Para obtener una copia local del proyecto, ejecute el siguiente comando en su terminal:

`git clone <URL_DEL_REPOSITORIO>`

Una vez descargado, acceda al directorio del proyecto:

`cd proyecto-8m`

## 3. Compilación
La aplicación se compila mediante la línea de comandos de Java. Asegúrese de situarse en la raíz del proyecto y siga estos pasos:

1. **Creación del directorio de destino:** `mkdir compilados`

2. **Compilación de las clases:** `javac -d compilados fuentes/*.java`

3. **Compilación de pruebas (HU1 - HU10):** `javac -cp ".;test/lib/junit-platform-console-standalone-1.9.2.jar" -d compilados fuentes/*.java test/tests/ProgramaTest.java`

## 4. Ejecución
El proyecto permite dos modos de ejecución:

* **Modo Aplicación (Usuario final):**
  Ejecute la clase principal para iniciar la dinámica de desmentir bulos:
  `java -cp compilados Programa`

* **Modo Verificación (Programador/QA):**
  Ejecute la suite de pruebas para verificar que el sistema cumple con todas las Historias de Usuario (HU1 a HU10):
  `java -jar test/lib/junit-platform-console-standalone-1.9.2.jar --class-path "compilados" --select-class ProgramaTest`

## 5. Desinstalación
Al tratarse de una aplicación portable, la desinstalación es un proceso manual que no requiere desinstalador gráfico:

1. **Eliminación de binarios:** Borre la carpeta `compilados/`.
2. **Eliminación de datos generados:** Borre la carpeta `resultados/` si desea eliminar el archivo `mejores.txt` y el histórico de puntuaciones.
3. **Eliminación del repositorio:** Borre la carpeta raíz del proyecto.