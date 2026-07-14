package model;

import util.ValidadorRut;

/**
 * Representa una persona base en el ecosistema de Llanquihue Tour.
 * Contiene datos personales y validaciones de datos clave.
 */
public class Persona implements Registrable {
    private String rut;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private Direccion direccion; // Composición

    /**
     * Constructor vacío.
     */
    public Persona() {
    }

    /**
     * Constructor completo.
     */
    public Persona(String rut, String nombre, String apellido, String correo, String telefono, Direccion direccion) {
        setRut(rut);
        this.nombre = nombre;
        this.apellido = apellido;
        setCorreo(correo);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // --- Getters, Setters y Validaciones ---

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if (!ValidadorRut.validarRut(rut)) {
            throw new IllegalArgumentException("El RUT ingresado no es válido: " + rut);
        }
        this.rut = ValidadorRut.formatearRut(rut);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        this.apellido = apellido.trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || !correo.contains("@") || !correo.contains(".")) {
            throw new IllegalArgumentException("El correo ingresado no es válido: " + correo);
        }
        this.correo = correo.trim().toLowerCase();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        this.telefono = telefono.trim();
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Representación textual de la Persona.
     */
    @Override
    public String toString() {
        return String.format("RUT: %s | Nombre: %s %s | Correo: %s | Teléfono: %s | Dirección: %s",
                rut, nombre, apellido, correo, telefono, (direccion != null ? direccion.toString() : "No definida"));
    }

    /**
     * Resumen básico de la persona (contrato de la interfaz Registrable).
     */
    @Override
    public String mostrarResumen() {
        return String.format("[PERSONA] %s %s — RUT: %s", nombre, apellido, rut);
    }
}
