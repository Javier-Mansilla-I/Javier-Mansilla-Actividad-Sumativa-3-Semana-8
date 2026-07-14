package model;

/**
 * Representa a un proveedor de servicios externo (ej. transportistas).
 * Hereda de Persona y añade la empresa y el tipo de servicio ofrecido.
 */
public class Proveedor extends Persona {
    private String empresa;
    private String tipoServicio;

    /**
     * Constructor vacío.
     */
    public Proveedor() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Proveedor(String rut, String nombre, String apellido, String correo, String telefono, Direccion direccion,
                     String empresa, String tipoServicio) {
        super(rut, nombre, apellido, correo, telefono, direccion);
        setEmpresa(empresa);
        setTipoServicio(tipoServicio);
    }

    // --- Getters y Setters ---

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        if (empresa == null || empresa.trim().isEmpty()) {
            throw new IllegalArgumentException("La empresa del proveedor no puede estar vacía.");
        }
        this.empresa = empresa.trim();
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        if (tipoServicio == null || tipoServicio.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de servicio no puede estar vacío.");
        }
        this.tipoServicio = tipoServicio.trim();
    }

    /**
     * Representación textual de Proveedor.
     */
    @Override
    public String toString() {
        return String.format("%s | Empresa: %s | Tipo de Servicio: %s",
                super.toString(), empresa, tipoServicio);
    }

    /**
     * Resumen específico del proveedor para la interfaz Registrable.
     */
    @Override
    public String mostrarResumen() {
        return String.format("[PROVEEDOR] %s %s — RUT: %s | Empresa: %s | Servicio: %s",
                getNombre(), getApellido(), getRut(), empresa, tipoServicio);
    }
}
