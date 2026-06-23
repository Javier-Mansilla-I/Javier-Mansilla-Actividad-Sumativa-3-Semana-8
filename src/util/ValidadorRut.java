package util;

/**
 * Clase utilitaria para la validación y formateo del RUT chileno.
 */
public class ValidadorRut {

    /**
     * Valida si un RUT chileno ingresado es correcto.
     * Admite formatos con puntos, guiones y espacios en blanco.
     */
    public static boolean validarRut(String rut) {
        if (rut == null || rut.trim().isEmpty()) {
            return false;
        }

        // Limpiar caracteres especiales y pasarlo a mayúsculas
        String limpio = rut.replace(".", "").replace("-", "").replace(" ", "").toUpperCase();

        if (limpio.length() < 8 || limpio.length() > 9) {
            return false;
        }

        try {
            String numeroStr = limpio.substring(0, limpio.length() - 1);
            char dv = limpio.charAt(limpio.length() - 1);

            int numero = Integer.parseInt(numeroStr);
            char dvEsperado = calcularDigitoVerificador(numero);

            return dv == dvEsperado;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Algoritmo de cálculo del dígito verificador.
     */
    private static char calcularDigitoVerificador(int rutNum) {
        int suma = 0;
        int multiplicador = 2;

        while (rutNum > 0) {
            int digito = rutNum % 10;
            suma += digito * multiplicador;
            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2;
            }
            rutNum /= 10;
        }

        int resto = suma % 11;
        int resultado = 11 - resto;

        if (resultado == 11) {
            return '0';
        } else if (resultado == 10) {
            return 'K';
        } else {
            return (char) ('0' + resultado);
        }
    }

    /**
     * Formatea un RUT al estándar chileno (XX.XXX.XXX-Y).
     */
    public static String formatearRut(String rut) {
        if (rut == null) return null;
        String limpio = rut.replace(".", "").replace("-", "").replace(" ", "").toUpperCase();
        if (limpio.length() < 8) return limpio;

        String dv = limpio.substring(limpio.length() - 1);
        String num = limpio.substring(0, limpio.length() - 1);

        StringBuilder sb = new StringBuilder();
        int len = num.length();
        for (int i = 0; i < len; i++) {
            sb.append(num.charAt(i));
            if ((len - 1 - i) % 3 == 0 && i != len - 1) {
                sb.append(".");
            }
        }
        sb.append("-").append(dv);
        return sb.toString();
    }
}
