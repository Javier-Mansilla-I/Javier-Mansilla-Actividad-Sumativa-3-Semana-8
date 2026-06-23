# LlanquihueTourApp V2.0

## Nombre del proyecto
LlanquihueTourApp V2.0

## Breve descripción del sistema desarrollado
Aplicación Java que muestra un mensaje de bienvenida y recorre un bucle sencillo, sirviendo como base para el desarrollo de funcionalidades de turismo en la región de Llanquihue.

## Paquetes utilizados y clases implementadas
- `app` – paquete para la lógica de la aplicación.
- `model` – modelos de datos.
- `service` – servicios de negocio.
- `util` – utilidades auxiliares.
- `Main.java` – clase principal que contiene el método `public static void main(String[] args)`.

## Instrucciones para ejecutar el programa
1. Compilar el proyecto:
   ```
   javac -d out src/**/*.java
   ```
2. Ejecutar la clase principal:
   ```
   java -cp out Main
   ```
   O utilizar el script `build_and_run.ps1` disponible en la raíz del proyecto.
