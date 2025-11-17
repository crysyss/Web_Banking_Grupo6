package web_banking_grupo6.modelo;

import java.time.LocalDateTime;

/**
 * Clase base para todas las transacciones del sistema.
 * Registra el tipo, monto, fecha y descripción de cada operación.
 */
public class Transaccion {
    private String idTransaccion;
    private String tipoTransaccion;  // "PAGO_SERVICIO", "PAGO_TARJETA", "CONSULTA_SALDO"
    private double monto;
    private LocalDateTime fecha;
    private String descripcion;
    private String estado;  // "EXITOSA", "FALLIDA", "PENDIENTE"
    
    private static int contadorId = 1000;  // Para generar IDs únicos
    
    public Transaccion(String tipoTransaccion, double monto, String descripcion) {
        this.idTransaccion = "TRX" + (contadorId++);
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.descripcion = descripcion;
        this.estado = "PENDIENTE";
    }
    
    // Getters y Setters
    
    public String getIdTransaccion() {
        return idTransaccion;
    }
    
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }
    
    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    
    public double getMonto() {
        return monto;
    }
    
    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
