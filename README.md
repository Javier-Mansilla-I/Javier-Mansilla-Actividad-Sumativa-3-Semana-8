# LlanquihueTourApp V2.0

## Descripción de lo desarrollado esta semana

Se implementó **polimorfismo** mediante una jerarquía de clases que modela distintos servicios turísticos:
- `ServicioTuristico` es la clase base con los atributos comunes `nombre` y `duración` y el método `mostrarInformacion()`.
- Subclases `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural` sobrescriben `mostrarInformacion()` para presentar información específica.
- Se creó la clase **`GestorServicios`** que genera una colección polimórfica (`List<ServicioTuristico>`) con al menos dos instancias de cada subclase.
- En `Main` se recorren y muestran estos servicios antes de iniciar el menú interactivo.
- Además, se solucionó un problema de compatibilidad de lectura de archivos en `GestorDatos` usando `BufferedReader` con `StandardCharsets.UTF_8` (compatible con Java 8+).
- Se añadió el script **`build_and_run.ps1`** que compila el proyecto, empaqueta el paquete `model` en un JAR y ejecuta la aplicación.

## Instrucciones para compilar y ejecutar el sistema

### Requisitos previos
- **JDK 8 o superior** (el script funciona con versiones 8‑23).
- **PowerShell** (Windows) para ejecutar el script de build.

### Pasos
1. **Abrir una terminal PowerShell** en la raíz del proyecto (`LlanquihueTourApp.2.0`).
2. Ejecutar el script de compilación y ejecución:
   ```powershell
   ./build_and_run.ps1
   ```
   El script realizará:
   - Limpieza de carpetas `bin/` y `dist/`.
   - Compilación de todo el código fuente Java.
   - Empaquetado de las clases del paquete `model` en `dist/lib/model.jar`.
   - Ejecución de la clase principal `ui.Main`.
3. Al iniciarse, la aplicación mostrará los servicios turísticos creados y luego presentará el menú interactivo para gestionar personas.

### Ejecutar sin el script (opcional)
Si prefieres compilar manualmente:
```bash
# Compilar
javac -d bin $(find src -name "*.java")
# Ejecutar
java -cp "bin;dist/lib/model.jar" ui.Main
```

