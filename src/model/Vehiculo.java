package model;

/**
 * Representa un Vehículo disponible en la flota de la agencia Llanquihue Tour.
 * Implementa directamente la interfaz Registrable como entidad independiente
 * (no extiende Persona ni ServicioTuristico).
 */
public class Vehiculo implements Registrable {

    private String patente;
    private String marca;
    private String modelo;
    private int capacidad;

    /**
     * Constructor vacío.
     */
    public Vehiculo() {
    }

    /**
     * Constructor completo.
     */
    public Vehiculo(String patente, String marca, String modelo, int capacidad) {
        setPatente(patente);
        setMarca(marca);
        setModelo(modelo);
        setCapacidad(capacidad);
    }

    // --- Getters y Setters ---

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente == null || patente.trim().isEmpty()) {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        }
        this.patente = patente.trim().toUpperCase();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca del vehículo no puede estar vacía.");
        }
        this.marca = marca.trim();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo del vehículo no puede estar vacío.");
        }
        this.modelo = modelo.trim();
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad del vehículo debe ser mayor a cero.");
        }
        this.capacidad = capacidad;
    }

    /**
     * Representación textual del Vehículo.
     */
    @Override
    public String toString() {
        return String.format("Patente: %s | Marca: %s | Modelo: %s | Capacidad: %d pasajeros",
                patente, marca, modelo, capacidad);
    }

    /**
     * Resumen específico del vehículo (implementación de Registrable).
     * Muestra patente, marca, modelo y capacidad de pasajeros.
     */
    @Override
    public String mostrarResumen() {
        return String.format("[VEHÍCULO] %s %s (Patente: %s) — Capacidad: %d pasajeros",
                marca, modelo, patente, capacidad);
    }
}
