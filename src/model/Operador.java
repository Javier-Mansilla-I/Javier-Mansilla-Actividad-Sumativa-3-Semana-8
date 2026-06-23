package model;

/**
 * Representa a un operador turístico local.
 * Hereda de Persona y añade la empresa y el tipo de operación.
 */
public class Operador extends Persona {
    private String empresa;
    private String tipoOperacion;

    /**
     * Constructor vacío.
     */
    public Operador() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Operador(String rut, String nombre, String apellido, String correo, String telefono, Direccion direccion,
                    String empresa, String tipoOperacion) {
        super(rut, nombre, apellido, correo, telefono, direccion);
        setEmpresa(empresa);
        setTipoOperacion(tipoOperacion);
    }

    // --- Getters y Setters ---

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        if (empresa == null || empresa.trim().isEmpty()) {
            throw new IllegalArgumentException("La empresa del operador no puede estar vacía.");
        }
        this.empresa = empresa.trim();
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        if (tipoOperacion == null || tipoOperacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de operación no puede estar vacío.");
        }
        this.tipoOperacion = tipoOperacion.trim();
    }

    /**
     * Representación textual de Operador.
     */
    @Override
    public String toString() {
        return String.format("%s | Empresa: %s | Tipo de Operación: %s",
                super.toString(), empresa, tipoOperacion);
    }
}
