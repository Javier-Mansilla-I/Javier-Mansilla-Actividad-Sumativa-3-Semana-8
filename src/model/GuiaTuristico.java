package model;

/**
 * Representa a un Guía Turístico de la agencia Llanquihue Tour.
 * Extiende Empleado (herencia) e implementa Registrable (polimorfismo).
 * Añade los atributos de idiomas disponibles y área de especialidad.
 */
public class GuiaTuristico extends Empleado {

    private String idiomas;
    private String areaEspecialidad;

    /**
     * Constructor vacío.
     */
    public GuiaTuristico() {
        super();
    }

    /**
     * Constructor completo.
     */
    public GuiaTuristico(String rut, String nombre, String apellido, String correo, String telefono,
                         Direccion direccion, String puesto, double sueldoBase,
                         String idiomas, String areaEspecialidad) {
        super(rut, nombre, apellido, correo, telefono, direccion, puesto, sueldoBase);
        setIdiomas(idiomas);
        setAreaEspecialidad(areaEspecialidad);
    }

    // --- Getters y Setters ---

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        if (idiomas == null || idiomas.trim().isEmpty()) {
            throw new IllegalArgumentException("Los idiomas del guía no pueden estar vacíos.");
        }
        this.idiomas = idiomas.trim();
    }

    public String getAreaEspecialidad() {
        return areaEspecialidad;
    }

    public void setAreaEspecialidad(String areaEspecialidad) {
        if (areaEspecialidad == null || areaEspecialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("El área de especialidad no puede estar vacía.");
        }
        this.areaEspecialidad = areaEspecialidad.trim();
    }

    /**
     * Representación textual del Guía Turístico.
     */
    @Override
    public String toString() {
        return String.format("%s | Idiomas: %s | Especialidad: %s",
                super.toString(), idiomas, areaEspecialidad);
    }

    /**
     * Resumen específico del guía turístico (implementación de Registrable).
     * Personaliza el mensaje indicando idiomas y área de especialidad.
     */
    @Override
    public String mostrarResumen() {
        return String.format("[GUÍA TURÍSTICO] %s %s — RUT: %s | Puesto: %s | Idiomas: %s | Especialidad: %s",
                getNombre(), getApellido(), getRut(), getPuesto(), idiomas, areaEspecialidad);
    }
}
