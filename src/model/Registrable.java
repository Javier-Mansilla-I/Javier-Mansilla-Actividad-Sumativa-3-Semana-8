package model;

/**
 * Interfaz de comportamiento común para todas las entidades gestionables del sistema.
 * Actúa como contrato que obliga a cada clase a describirse mediante un resumen.
 */
public interface Registrable {

    /**
     * Retorna un texto descriptivo con los datos más relevantes de la entidad.
     * Cada clase implementadora personaliza este mensaje según su tipo.
     *
     * @return resumen legible de la entidad.
     */
    String mostrarResumen();
}
