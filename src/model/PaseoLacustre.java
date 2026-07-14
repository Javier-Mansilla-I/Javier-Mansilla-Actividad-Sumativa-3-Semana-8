package model;

/**
 * Subclase que representa un Paseo Lacustre.
 */
public class PaseoLacustre extends ServicioTuristico {
    private String tipoEmbarcacion;

    public PaseoLacustre(String nombre, int duracionHoras, String tipoEmbarcacion) {
        super(nombre, duracionHoras);
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: PASEO LACUSTRE | Embarcación: " + tipoEmbarcacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

    /**
     * Resumen específico del paseo lacustre.
     */
    @Override
    public String mostrarResumen() {
        return String.format("[PASEO LACUSTRE] %s — Duración: %d horas | Embarcación: %s",
                getNombre(), getDuracionHoras(), tipoEmbarcacion);
    }
}
