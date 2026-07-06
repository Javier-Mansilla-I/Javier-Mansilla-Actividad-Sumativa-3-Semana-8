package data;

import model.*;
import util.ValidadorRut;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Servicio encargado de la lectura y procesamiento de archivos planos para Llanquihue Tour.
 * Implementa controles de excepciones para validar la robustez de los datos.
 */
public class GestorDatos {

    /**
     * Carga las personas desde un archivo delimitado por ';' a un ArrayList dinámico.
     */
    public static ArrayList<Persona> leerPersonas(String rutaArchivo) {
        ArrayList<Persona> personas = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.err.println("[Error] El archivo de datos no existe: " + archivo.getAbsolutePath());
            return personas;
        }

        try (BufferedReader br = Files.newBufferedReader(Paths.get(rutaArchivo), StandardCharsets.UTF_8)) {
            String linea;
            int nroLinea = 0;

            while ((linea = br.readLine()) != null) {
                nroLinea++;
                String lineaTrim = linea.trim();

                // Ignorar comentarios y líneas vacías
                if (lineaTrim.isEmpty() || lineaTrim.startsWith("#")) {
                    continue;
                }

                try {
                    String[] datos = lineaTrim.split(";");
                    if (datos.length < 10) {
                        throw new IllegalArgumentException("Campos incompletos para construir el registro.");
                    }

                    String tipo = datos[0].trim().toUpperCase();
                    String rut = datos[1].trim();
                    String nombre = datos[2].trim();
                    String apellido = datos[3].trim();
                    String correo = datos[4].trim();
                    String telefono = datos[5].trim();
                    
                    // Composición: Dirección
                    String calle = datos[6].trim();
                    String numero = datos[7].trim();
                    String comuna = datos[8].trim();
                    String ciudad = datos[9].trim();
                    Direccion direccion = new Direccion(calle, numero, ciudad, comuna);

                    // Evitar duplicidades de RUT
                    if (existeRut(personas, rut)) {
                        System.out.printf("[ADVERTENCIA] Línea %d omitida: El RUT '%s' ya está registrado en la colección.%n", nroLinea, ValidadorRut.formatearRut(rut));
                        continue;
                    }

                    Persona persona;
                    switch (tipo) {
                        case "EMPLEADO":
                            if (datos.length < 12) {
                                throw new IllegalArgumentException("Faltan campos específicos para el tipo EMPLEADO (requiere puesto y sueldo base).");
                            }
                            String puesto = datos[10].trim();
                            double sueldoBase = Double.parseDouble(datos[11].trim());
                            persona = new Empleado(rut, nombre, apellido, correo, telefono, direccion, puesto, sueldoBase);
                            break;

                        case "OPERADOR":
                            if (datos.length < 12) {
                                throw new IllegalArgumentException("Faltan campos específicos para el tipo OPERADOR (requiere empresa y tipo de operación).");
                            }
                            String empresaOp = datos[10].trim();
                            String tipoOperacion = datos[11].trim();
                            persona = new Operador(rut, nombre, apellido, correo, telefono, direccion, empresaOp, tipoOperacion);
                            break;

                        case "PROVEEDOR":
                            if (datos.length < 12) {
                                throw new IllegalArgumentException("Faltan campos específicos para el tipo PROVEEDOR (requiere empresa y tipo de servicio).");
                            }
                            String empresaProv = datos[10].trim();
                            String tipoServicio = datos[11].trim();
                            persona = new Proveedor(rut, nombre, apellido, correo, telefono, direccion, empresaProv, tipoServicio);
                            break;

                        default:
                            throw new IllegalArgumentException("Tipo de registro no soportado: " + tipo);
                    }

                    personas.add(persona);

                } catch (NumberFormatException e) {
                    System.err.printf("[ERROR DE FORMATO] Línea %d omitida: No se pudo parsear el valor numérico. Detalle: %s%n", nroLinea, e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.printf("[ERROR DE VALIDACIÓN] Línea %d omitida: %s%n", nroLinea, e.getMessage());
                } catch (Exception e) {
                    System.err.printf("[ERROR INESPERADO] Línea %d omitida: %s%n", nroLinea, e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("[Error de Lectura] No se pudo leer el archivo: " + e.getMessage());
        }

        return personas;
    }

    /**
     * Verifica la existencia del RUT en la colección actual.
     */
    private static boolean existeRut(ArrayList<Persona> personas, String rut) {
        String rutFormateado = ValidadorRut.validarRut(rut) ? ValidadorRut.formatearRut(rut) : rut;
        for (Persona p : personas) {
            if (p.getRut().equalsIgnoreCase(rutFormateado)) {
                return true;
            }
        }
        return false;
    }
}
