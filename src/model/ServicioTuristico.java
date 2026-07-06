package model;

/**
 * Superclase que representa un Servicio Turístico.
 */
public class ServicioTuristico {
    private String nombre;
    private int duracionHoras;

    public ServicioTuristico(String nombre, int duracionHoras) {
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    @Override
    public String toString() {
        return "Servicio: " + nombre + " | Duración: " + duracionHoras + " hrs";
    }

    /**
     * Muestra la información básica del servicio turístico.
     */
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
}

