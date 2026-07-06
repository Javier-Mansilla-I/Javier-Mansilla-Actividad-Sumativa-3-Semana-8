package ui;

import model.*;
import data.GestorDatos;
import data.GestorServicios;
import util.ValidadorRut;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Entrada principal para la ejecución de Llanquihue Tour v2.0.
 */
public class Main {

    public static void main(String[] args) {
        // --- PRUEBA DE JERARQUÍA DE SERVICIOS TURÍSTICOS (Paso 3) ---
        System.out.println("=====================================================================");
        System.out.println("            SERVICIOS TURÍSTICOS DISPONIBLES (PRUEBA)                ");
        System.out.println("=====================================================================");
        List<ServicioTuristico> servicios = GestorServicios.obtenerServiciosDePrueba();
        for (ServicioTuristico s : servicios) {
            s.mostrarInformacion();
        }
        System.out.println("=====================================================================\n");

        String rutaArchivo = "resources/personas.txt";

        System.out.println("=====================================================================");
        System.out.println("            SISTEMA LLANQUIHUE TOUR v2.0 (MODULAR)                  ");
        System.out.println("=====================================================================");
        System.out.println("[Carga] Procesando archivo de datos: " + rutaArchivo);
        System.out.println("---------------------------------------------------------------------");

        // Cargar colección dinámica ArrayList de Personas
        ArrayList<Persona> personas = GestorDatos.leerPersonas(rutaArchivo);

        System.out.println("---------------------------------------------------------------------");
        System.out.printf("[Carga] Finalizada. Registros cargados con éxito: %d%n", personas.size());
        System.out.println("=====================================================================");

        if (personas.isEmpty()) {
            System.out.println("[Advertencia] No se pudieron cargar registros válidos. Finalizando.");
            return;
        }

        Scanner scanner = new Scanner(System.in, "UTF-8");
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                System.out.print("Seleccione una opción (1-5): ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar salto de línea

                switch (opcion) {
                    case 1:
                        mostrarColeccionCompleta(personas);
                        break;
                    case 2:
                        realizarBusquedaPorRut(personas, scanner);
                        break;
                    case 3:
                        realizarFiltroPorClase(personas, scanner);
                        break;
                    case 4:
                        realizarFiltrosDeNegocio(personas, scanner);
                        break;
                    case 5:
                        salir = true;
                        System.out.println("\nCerrando el Sistema de Gestión Llanquihue Tour. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("\n[Error] Opción fuera de rango (1-5).");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[Error] Entrada inválida. Ingrese solo números enteros.");
                scanner.nextLine(); // Limpiar búfer
            }

            if (!salir) {
                System.out.println("\nPresione ENTER para regresar al menú...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=====================================================================");
        System.out.println("                       MENÚ DE OPERACIONES                           ");
        System.out.println("=====================================================================");
        System.out.println("1. Mostrar todos los registros cargados (Recorrido)");
        System.out.println("2. Buscar persona por RUT");
        System.out.println("3. Filtrar personas por Tipo (Empleado / Operador / Proveedor)");
        System.out.println("4. Filtros Avanzados (Sueldo base / Comuna)");
        System.out.println("5. Salir del programa");
        System.out.println("=====================================================================");
    }

    private static void mostrarColeccionCompleta(ArrayList<Persona> personas) {
        System.out.println("\n--- LISTADO COMPLETO ---");
        for (int i = 0; i < personas.size(); i++) {
            Persona p = personas.get(i);
            System.out.printf("%d. [%s] %s%n", (i + 1), p.getClass().getSimpleName().toUpperCase(), p);
        }
    }

    private static void realizarBusquedaPorRut(ArrayList<Persona> personas, Scanner scanner) {
        System.out.println("\n--- BUSCAR POR RUT ---");
        System.out.print("Ingrese RUT a consultar: ");
        String rut = scanner.nextLine().trim();

        if (!ValidadorRut.validarRut(rut)) {
            System.out.println("[Error] RUT no válido.");
            return;
        }

        String rutFormat = ValidadorRut.formatearRut(rut);
        boolean hallado = false;

        for (Persona p : personas) {
            if (p.getRut().equalsIgnoreCase(rutFormat)) {
                System.out.println("\n¡Registro Encontrado!");
                System.out.println("Tipo: " + p.getClass().getSimpleName().toUpperCase());
                System.out.println(p);
                hallado = true;
                break;
            }
        }

        if (!hallado) {
            System.out.println("\nNo se encontró a nadie con el RUT: " + rutFormat);
        }
    }

    private static void realizarFiltroPorClase(ArrayList<Persona> personas, Scanner scanner) {
        System.out.println("\n--- FILTRAR POR TIPO ---");
        System.out.println("1. Empleados");
        System.out.println("2. Operadores");
        System.out.println("3. Proveedores");
        System.out.print("Seleccione (1-3): ");

        try {
            int sel = scanner.nextInt();
            scanner.nextLine();

            Class<?> filtroClase;
            String label;

            switch (sel) {
                case 1:
                    filtroClase = Empleado.class;
                    label = "EMPLEADOS";
                    break;
                case 2:
                    filtroClase = Operador.class;
                    label = "OPERADORES";
                    break;
                case 3:
                    filtroClase = Proveedor.class;
                    label = "PROVEEDORES";
                    break;
                default:
                    System.out.println("[Error] Selección inválida.");
                    return;
            }

            System.out.printf("\n--- RESULTADOS PARA EL TIPO: %s ---%n", label);
            int count = 0;
            for (Persona p : personas) {
                if (filtroClase.isInstance(p)) {
                    count++;
                    System.out.println("- " + p);
                }
            }
            if (count == 0) {
                System.out.println("No se hallaron registros de este tipo.");
            } else {
                System.out.println("Total encontrados: " + count);
            }
        } catch (InputMismatchException e) {
            System.out.println("[Error] Entrada inválida.");
            scanner.nextLine();
        }
    }

    private static void realizarFiltrosDeNegocio(ArrayList<Persona> personas, Scanner scanner) {
        System.out.println("\n--- FILTROS AVANZADOS ---");
        System.out.println("1. Filtrar personas por Comuna");
        System.out.println("2. Filtrar Empleados por Sueldo Mínimo");
        System.out.print("Seleccione (1-2): ");

        try {
            int sel = scanner.nextInt();
            scanner.nextLine();

            if (sel == 1) {
                System.out.print("Comuna a buscar: ");
                String comuna = scanner.nextLine().trim();

                System.out.printf("\n--- RESULTADOS PARA COMUNA: '%s' ---%n", comuna);
                int count = 0;
                for (Persona p : personas) {
                    if (p.getDireccion() != null && p.getDireccion().getComuna().equalsIgnoreCase(comuna)) {
                        count++;
                        System.out.printf("- %s %s (%s) | RUT: %s | Dirección: %s%n",
                                p.getNombre(), p.getApellido(), p.getClass().getSimpleName().toUpperCase(),
                                p.getRut(), p.getDireccion());
                    }
                }
                if (count == 0) {
                    System.out.println("No hay registros en esa comuna.");
                } else {
                    System.out.println("Total encontrados: " + count);
                }
            } else if (sel == 2) {
                System.out.print("Monto de sueldo mínimo: $");
                double monto = scanner.nextDouble();
                scanner.nextLine();

                System.out.printf("\n--- EMPLEADOS CON SUELDO BASE >= $%,.0f ---%n", monto);
                int count = 0;
                for (Persona p : personas) {
                    if (p instanceof Empleado) {
                        Empleado emp = (Empleado) p;
                        if (emp.getSueldoBase() >= monto) {
                            count++;
                            System.out.printf("- %s %s | Puesto: %s | Sueldo: $%,.0f%n",
                                    emp.getNombre(), emp.getApellido(), emp.getPuesto(), emp.getSueldoBase());
                        }
                    }
                }
                if (count == 0) {
                    System.out.println("Ningún empleado cumple la condición.");
                } else {
                    System.out.println("Total encontrados: " + count);
                }
            } else {
                System.out.println("[Error] Selección fuera de rango.");
            }
        } catch (InputMismatchException e) {
            System.out.println("[Error] Entrada inválida.");
            scanner.nextLine();
        }
    }
}
