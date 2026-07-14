package data;

import model.*;

import java.util.ArrayList;

/**
 * Gestiona una colección genérica de entidades registrables del sistema Llanquihue Tour.
 * Utiliza ArrayList<Registrable> para almacenar objetos de distintas clases que comparten
 * el contrato de la interfaz Registrable (polimorfismo).
 */
public class GestorEntidades {

    /** Colección genérica que almacena cualquier entidad que implemente Registrable. */
    private ArrayList<Registrable> entidades;

    /**
     * Constructor: inicializa la colección vacía.
     */
    public GestorEntidades() {
        this.entidades = new ArrayList<>();
    }

    /**
     * Agrega un nuevo objeto Registrable a la colección.
     *
     * @param entidad objeto que implementa Registrable.
     */
    public void agregarEntidad(Registrable entidad) {
        if (entidad == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula.");
        }
        entidades.add(entidad);
    }

    /**
     * Retorna la colección completa de entidades.
     *
     * @return ArrayList<Registrable> con todas las entidades registradas.
     */
    public ArrayList<Registrable> getEntidades() {
        return entidades;
    }

    /**
     * Retorna la cantidad de entidades registradas.
     *
     * @return número de entidades en la colección.
     */
    public int cantidad() {
        return entidades.size();
    }

    /**
     * Recorre la colección y devuelve un String con los resúmenes de todos los objetos,
     * usando instanceof para aplicar lógica diferenciada según el tipo de cada entidad.
     *
     * @return texto consolidado con todos los resúmenes.
     */
    public String obtenerResumenes() {
        if (entidades.isEmpty()) {
            return "No hay entidades registradas en el sistema.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════════════\n");
        sb.append("          REGISTRO DE ENTIDADES — LLANQUIHUE TOUR              \n");
        sb.append("═══════════════════════════════════════════════════════════════\n");

        int indice = 1;

        // Recorrido con for-each sobre la colección genérica
        for (Registrable entidad : entidades) {

            // Polimorfismo: se llama al método mostrarResumen() de cada clase
            sb.append(String.format("%d. %s%n", indice++, entidad.mostrarResumen()));

            // instanceof: lógica diferenciada según el tipo específico del objeto
            if (entidad instanceof GuiaTuristico) {
                GuiaTuristico guia = (GuiaTuristico) entidad;
                sb.append(String.format("   ↳ Idiomas disponibles: %s | Área: %s%n",
                        guia.getIdiomas(), guia.getAreaEspecialidad()));

            } else if (entidad instanceof Empleado) {
                Empleado emp = (Empleado) entidad;
                sb.append(String.format("   ↳ Sueldo base: $%,.0f%n", emp.getSueldoBase()));

            } else if (entidad instanceof Operador) {
                Operador op = (Operador) entidad;
                sb.append(String.format("   ↳ Tipo de operación: %s%n", op.getTipoOperacion()));

            } else if (entidad instanceof Proveedor) {
                Proveedor prov = (Proveedor) entidad;
                sb.append(String.format("   ↳ Tipo de servicio: %s%n", prov.getTipoServicio()));

            } else if (entidad instanceof Vehiculo) {
                Vehiculo v = (Vehiculo) entidad;
                sb.append(String.format("   ↳ Patente: %s | Capacidad: %d pasajeros%n",
                        v.getPatente(), v.getCapacidad()));

            } else if (entidad instanceof ExcursionCultural) {
                ExcursionCultural exc = (ExcursionCultural) entidad;
                sb.append(String.format("   ↳ Lugar histórico: %s%n", exc.getLugarHistorico()));

            } else if (entidad instanceof PaseoLacustre) {
                PaseoLacustre pl = (PaseoLacustre) entidad;
                sb.append(String.format("   ↳ Embarcación: %s%n", pl.getTipoEmbarcacion()));

            } else if (entidad instanceof RutaGastronomica) {
                RutaGastronomica rg = (RutaGastronomica) entidad;
                sb.append(String.format("   ↳ Número de paradas: %d%n", rg.getNumeroDeParadas()));

            } else if (entidad instanceof ServicioTuristico) {
                ServicioTuristico st = (ServicioTuristico) entidad;
                sb.append(String.format("   ↳ Duración: %d horas%n", st.getDuracionHoras()));
            }
        }

        sb.append("═══════════════════════════════════════════════════════════════\n");
        sb.append(String.format("   Total de entidades registradas: %d%n", entidades.size()));
        sb.append("═══════════════════════════════════════════════════════════════");

        return sb.toString();
    }

    /**
     * Inicializa la colección con datos de demostración para prueba del sistema.
     */
    public void cargarDatosDemostracion() {
        Direccion dir = new Direccion("Av. Llanquihue", "1200", "Puerto Varas", "Puerto Varas");

        // Guía Turístico (subclase de Empleado)
        agregarEntidad(new GuiaTuristico(
                "12345678-5", "Valentina", "Soto", "vsoto@llanquihuetour.cl", "+56912345678",
                dir, "Guía Turístico", 850000, "Español, Inglés, Alemán", "Patrimonio Colonial"));

        // Empleado base
        agregarEntidad(new Empleado(
                "98765432-5", "Carlos", "Muñoz", "cmuñoz@llanquihuetour.cl", "+56987654321",
                dir, "Administrador", 1200000));

        // Operador
        agregarEntidad(new Operador(
                "11111111-1", "María", "González", "mgonzalez@lakesoperator.cl", "+56911111111",
                dir, "Lakes Operator SpA", "Turismo Lacustre"));

        // Proveedor
        agregarEntidad(new Proveedor(
                "22222222-2", "Pedro", "Astorga", "pastorga@transportes.cl", "+56922222222",
                dir, "Transportes Sur", "Traslados Aeropuerto"));

        // Vehículo
        agregarEntidad(new Vehiculo("BBLK-45", "Mercedes-Benz", "Sprinter", 19));
        agregarEntidad(new Vehiculo("CCMN-78", "Toyota", "HiAce", 12));

        // Servicios turísticos
        agregarEntidad(new ExcursionCultural("Tour Histórico Puerto Varas", 3, "Iglesia del Sagrado Corazón"));
        agregarEntidad(new PaseoLacustre("Navegación Bahía Frutillar", 2, "Catamarán"));
        agregarEntidad(new RutaGastronomica("Ruta del Queso y Sabores Locales", 4, 3));
    }
}
