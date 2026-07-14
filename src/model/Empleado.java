package model;

/**
 * Representa un empleado de la organización (ej. guías de turismo, administradores).
 * Hereda los datos de Persona y añade puesto y sueldo.
 */
public class Empleado extends Persona {
    private String puesto;
    private double sueldoBase;

    /**
     * Constructor vacío.
     */
    public Empleado() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Empleado(String rut, String nombre, String apellido, String correo, String telefono, Direccion direccion,
                    String puesto, double sueldoBase) {
        super(rut, nombre, apellido, correo, telefono, direccion);
        this.puesto = puesto;
        setSueldoBase(sueldoBase);
    }

    // --- Getters, Setters y Validaciones ---

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        if (puesto == null || puesto.trim().isEmpty()) {
            throw new IllegalArgumentException("El puesto del empleado no puede estar vacío.");
        }
        this.puesto = puesto.trim();
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        if (sueldoBase < 0) {
            throw new IllegalArgumentException("El sueldo base no puede ser negativo: " + sueldoBase);
        }
        this.sueldoBase = sueldoBase;
    }

    /**
     * Representación textual de Empleado, incluyendo los datos heredados.
     */
    @Override
    public String toString() {
        return String.format("%s | Puesto: %s | Sueldo Base: $%,.0f",
                super.toString(), puesto, sueldoBase);
    }

    /**
     * Resumen específico del empleado para la interfaz Registrable.
     */
    @Override
    public String mostrarResumen() {
        return String.format("[EMPLEADO] %s %s — RUT: %s | Puesto: %s | Sueldo Base: $%,.0f",
                getNombre(), getApellido(), getRut(), puesto, sueldoBase);
    }
}
