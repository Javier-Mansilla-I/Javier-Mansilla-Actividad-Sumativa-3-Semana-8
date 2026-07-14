package ui;

import data.GestorEntidades;

/**
 * Punto de entrada independiente para la interfaz gráfica de LlanquihueTourApp.
 * Lanza la GUI sin depender del flujo de consola de Main.java.
 */
public class MainGUI {

    public static void main(String[] args) {
        // Crear el gestor de entidades con colección ArrayList<Registrable>
        GestorEntidades gestor = new GestorEntidades();

        // Cargar datos de demostración (objetos de distintas clases que implementan Registrable)
        gestor.cargarDatosDemostracion();

        // Lanzar la interfaz gráfica en el hilo de Swing (Event Dispatch Thread)
        AppGUI.lanzar(gestor);
    }
}
