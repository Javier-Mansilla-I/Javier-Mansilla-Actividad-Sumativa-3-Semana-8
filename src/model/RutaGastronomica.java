package model;

/**
 * Subclase que representa una Ruta Gastronómica.
 */
public class RutaGastronomica extends ServicioTuristico {
    private int numeroDeParadas;

    public RutaGastronomica(String nombre, int duracionHoras, int numeroDeParadas) {
        super(nombre, duracionHoras);
        this.numeroDeParadas = numeroDeParadas;
    }

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: RUTA GASTRONÓMICA | Paradas: " + numeroDeParadas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

    /**
     * Resumen específico de la ruta gastronómica.
     */
    @Override
    public String mostrarResumen() {
        return String.format("[RUTA GASTRONÓMICA] %s — Duración: %d horas | Paradas: %d",
                getNombre(), getDuracionHoras(), numeroDeParadas);
    }
}
