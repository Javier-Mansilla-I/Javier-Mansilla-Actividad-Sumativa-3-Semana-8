package model;

/**
 * Subclase que representa una Excursión Cultural.
 */
public class ExcursionCultural extends ServicioTuristico {
    private String lugarHistorico;

    public ExcursionCultural(String nombre, int duracionHoras, String lugarHistorico) {
        super(nombre, duracionHoras);
        this.lugarHistorico = lugarHistorico;
    }

    public String getLugarHistorico() {
        return lugarHistorico;
    }

    public void setLugarHistorico(String lugarHistorico) {
        this.lugarHistorico = lugarHistorico;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: EXCURSIÓN CULTURAL | Lugar Histórico: " + lugarHistorico;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
}
