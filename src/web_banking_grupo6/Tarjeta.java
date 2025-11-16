package web_banking_grupo6;

/**
 * Representa una tarjeta de crédito asociada a un cliente.
 * Maneja el límite de crédito y la deuda acumulada.
 */
public class Tarjeta {
    private String numeroTarjeta;
    private String tipoTarjeta;  // "Crédito", "Débito"
    private double limiteCredito;
    private double deudaActual;
    private String fechaVencimiento;
    
    public Tarjeta(String numeroTarjeta, String tipoTarjeta, double limiteCredito, String fechaVencimiento) {
        this.numeroTarjeta = numeroTarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.limiteCredito = limiteCredito;
        this.deudaActual = 0.0;
        this.fechaVencimiento = fechaVencimiento;
    }
    
    /**
     * Agrega una compra a la deuda de la tarjeta.
     * TODO: Validar que no se supere el límite de crédito.
     */
    public void agregarDeuda(double monto) {
        if (deudaActual + monto <= limiteCredito) {
            deudaActual += monto;
        } else {
            throw new IllegalStateException("Excede el límite de crédito disponible");
        }
    }
    
    /**
     * Registra un pago a la tarjeta, reduciendo la deuda.
     */
    public void pagarDeuda(double monto) {
        if (monto > 0 && monto <= deudaActual) {
            deudaActual -= monto;
        } else if (monto > deudaActual) {
            // Si paga más de lo que debe, ajustar a la deuda exacta
            deudaActual = 0.0;
        }
    }
    
    public double getCreditoDisponible() {
        return limiteCredito - deudaActual;
    }
    
    // Getters y Setters
    
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }
    
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
    
    public double getLimiteCredito() {
        return limiteCredito;
    }
    
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
    
    public double getDeudaActual() {
        return deudaActual;
    }
    
    public void setDeudaActual(double deudaActual) {
        this.deudaActual = deudaActual;
    }
    
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
    
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
