package data;

import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor encargado de crear instancias de prueba para los Servicios Turísticos.
 */
public class GestorServicios {

    /**
     * Crea y retorna una lista con al menos dos instancias de cada tipo de Servicio Turístico.
     */
    public static List<ServicioTuristico> obtenerServiciosDePrueba() {
        ArrayList<ServicioTuristico> servicios = new ArrayList<>();

        // 2 instancias de RutaGastronomica
        servicios.add(new RutaGastronomica("Ruta del Queso y Sabores Locales", 4, 3));
        servicios.add(new RutaGastronomica("Sabores de Llanquihue y Cerveza Artesanal", 5, 4));

        // 2 instancias de PaseoLacustre
        servicios.add(new PaseoLacustre("Navegación Bahía de Frutillar", 2, "Catamarán"));
        servicios.add(new PaseoLacustre("Aventura en Kayak Lago Llanquihue", 3, "Kayak Doble"));

        // 2 instancias de ExcursionCultural
        servicios.add(new ExcursionCultural("Tour Histórico Puerto Varas", 3, "Iglesia del Sagrado Corazón"));
        servicios.add(new ExcursionCultural("Ruta de los Colonos y Arquitectura", 6, "Museo Colonial Alemán de Frutillar"));

        return servicios;
    }
}
