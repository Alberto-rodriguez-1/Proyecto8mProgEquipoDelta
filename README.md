# ♀️ PROYECTO: MEMES 8M

Sistema interactivo diseñado para combatir bulos mediante la confrontación de memes con datos reales verificados. El programa gestiona archivos, valida respuestas y mantiene un ranking histórico de las mejores puntuaciones.

---

## 👥 INTEGRANTES
* **Nicanor Andrés Gil Moreno**
* **Alberto Rodriguez Piris**
* **Mario Carmona Parejo**

---

## 🕹️ FUNCIONAMIENTO
1. **Validación:** El sistema verifica la integridad de los directorios y archivos de datos al arrancar.
2. **Carga:** Se parsean los memes, realidades y soluciones desde archivos `.txt`, `.json` y `.xml`.
3. **Partida:** El usuario debe desmentir el meme seleccionando la realidad correcta. Cada acierto suma un punto.
4. **Ranking:** Al finalizar 5 rondas, si la puntuación entra en el **Top 3**, se registra el nombre del usuario en el histórico de resultados.

---

## 📁 ESTRUCTURA DEL PROYECTO
```text
├───datos                <-- Ficheros: memes.txt, realidades.json, soluciones.xml
├───documentacion
│   └───javadoc          <-- Documentación técnica (HTML)
├───fuentes
│   └───compilados       <-- Binarios .class ejecutables
├───jdk-25               <-- Entorno de ejecución (JDK portable)
│   ├───bin
│   ├───conf
│   ├───include
│   ├───jmods
│   ├───legal
│   └───lib
└───test
    └───lib              <-- Librerías de testeo
