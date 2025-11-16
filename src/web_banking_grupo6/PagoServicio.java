package web_banking_grupo6;

/**
 * Representa un pago de servicio específico.
 * Extiende Transaccion para agregar información específica del servicio pagado.
 */
public class PagoServicio extends Transaccion {
    private Servicio servicio;
    private String numeroReferencia;  // Número de cliente o referencia del servicio
    
    public PagoServicio(double monto, Servicio servicio, String numeroReferencia) {
        super("PAGO_SERVICIO", monto, "Pago de " + servicio.getNombreServicio());
        this.servicio = servicio;
        this.numeroReferencia = numeroReferencia;
    }
    
    // Getters y Setters
    
    public Servicio getServicio() {
        return servicio;
    }
    
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    public String getNumeroReferencia() {
        return numeroReferencia;
    }
    
    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
}
