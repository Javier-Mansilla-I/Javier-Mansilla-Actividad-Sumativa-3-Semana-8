package model;

/**
 * Representa una dirección física asociada a personas y organizaciones.
 */
public class Direccion {
    private String calle;
    private String numero;
    private String ciudad;
    private String comuna;

    /**
     * Constructor vacío.
     */
    public Direccion() {
    }

    /**
     * Constructor completo.
     */
    public Direccion(String calle, String numero, String ciudad, String comuna) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.comuna = comuna;
    }

    // --- Getters y Setters ---

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * Representación textual legible de la dirección.
     */
    @Override
    public String toString() {
        return String.format("%s %s, %s, %s", calle, numero, comuna, ciudad);
    }
}
