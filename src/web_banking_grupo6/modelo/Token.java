package web_banking_grupo6.modelo;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Representa un token de seguridad para autorizar operaciones sensibles.
 * El token tiene un tiempo de vida limitado y solo puede usarse una vez.
 */
public class Token {
    private String codigoToken;
    private LocalDateTime fechaGeneracion;
    private LocalDateTime fechaExpiracion;
    private boolean utilizado;
    private String operacionAsociada;  // "CONSULTA_SALDO", "PAGO_SERVICIO", etc.
    
    // Los tokens expiran en 5 minutos (requisito de seguridad)
    private static final int MINUTOS_VALIDEZ = 5;
    
    public Token(String operacionAsociada) {
        this.codigoToken = generarCodigoAleatorio();
        this.fechaGeneracion = LocalDateTime.now();
        this.fechaExpiracion = fechaGeneracion.plusMinutes(MINUTOS_VALIDEZ);
        this.utilizado = false;
        this.operacionAsociada = operacionAsociada;
    }
    
    /**
     * Genera un código de 6 dígitos aleatorio para el token.
     * TODO: En producción, deberíamos usar un generador criptográfico más robusto.
     */
    private String generarCodigoAleatorio() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }
    
    /**
     * Valida si el token sigue siendo válido (no expiró y no fue usado).
     */
    public boolean esValido() {
        if (utilizado) {
            return false;
        }
        
        LocalDateTime ahora = LocalDateTime.now();
        return ahora.isBefore(fechaExpiracion);
    }
    
    /**
     * Marca el token como utilizado. Esto previene ataques de replay.
     */
    public void marcarComoUtilizado() {
        this.utilizado = true;
    }
    
    // Getters y Setters
    
    public String getCodigoToken() {
        return codigoToken;
    }
    
    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }
    
    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }
    
    public boolean isUtilizado() {
        return utilizado;
    }
    
    public String getOperacionAsociada() {
        return operacionAsociada;
    }
}
