package web_banking_grupo6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Representa un comprobante o ticket de una transacción bancaria.
 * Genera documentos imprimibles para todas las operaciones del sistema.
 * 
 * @author Grupo 6
 * @version 1.0
 */
public class Comprobante {
    
    private String numeroComprobante;
    private String tipoOperacion;
    private LocalDateTime fechaHora;
    private String numeroCuenta;
    private String cuentaDestino;  // Para transferencias
    private String descripcion;
    private double monto;
    private String estado;
    private String nombreCliente;
    
    private static int contadorComprobantes = 1000;
    private static final NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "PY"));
    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    /**
     * Constructor para crear un comprobante de transacción.
     */
    public Comprobante(String tipoOperacion, String numeroCuenta, String descripcion, 
                       double monto, String estado, String nombreCliente) {
        this.numeroComprobante = "COMP-" + String.format("%06d", contadorComprobantes++);
        this.tipoOperacion = tipoOperacion;
        this.fechaHora = LocalDateTime.now();
        this.numeroCuenta = numeroCuenta;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
        this.cuentaDestino = null;
    }
    
    /**
     * Constructor para transferencias (incluye cuenta destino).
     */
    public Comprobante(String tipoOperacion, String numeroCuenta, String cuentaDestino,
                       String descripcion, double monto, String estado, String nombreCliente) {
        this(tipoOperacion, numeroCuenta, descripcion, monto, estado, nombreCliente);
        this.cuentaDestino = cuentaDestino;
    }
    
    /**
     * Genera el texto formateado del comprobante listo para imprimir o mostrar.
     * Incluye todos los detalles de la transacción en formato legible.
     */
    public String generarTextoComprobante() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("═══════════════════════════════════════════════════\n");
        sb.append("              COMPROBANTE DE TRANSACCIÓN           \n");
        sb.append("                 WEB BANKING SYSTEM                \n");
        sb.append("═══════════════════════════════════════════════════\n\n");
        
        sb.append(String.format("Comprobante N°:  %s\n", numeroComprobante));
        sb.append(String.format("Fecha y Hora:    %s\n", fechaHora.format(formatoFecha)));
        sb.append(String.format("Tipo Operación:  %s\n", tipoOperacion));
        sb.append(String.format("Estado:          %s\n\n", estado));
        
        sb.append("───────────────────────────────────────────────────\n");
        sb.append("               DATOS DE LA TRANSACCIÓN            \n");
        sb.append("───────────────────────────────────────────────────\n\n");
        
        sb.append(String.format("Cliente:         %s\n", nombreCliente));
        sb.append(String.format("Cuenta Origen:   %s\n", numeroCuenta));
        
        if (cuentaDestino != null && !cuentaDestino.isEmpty()) {
            sb.append(String.format("Cuenta Destino:  %s\n", cuentaDestino));
        }
        
        sb.append(String.format("Descripción:     %s\n", descripcion));
        sb.append(String.format("Monto:           Gs. %,.0f\n\n", monto));
        
        sb.append("═══════════════════════════════════════════════════\n");
        sb.append("    Este comprobante es válido como constancia     \n");
        sb.append("         de la operación realizada.                \n");
        sb.append("═══════════════════════════════════════════════════\n");
        
        return sb.toString();
    }
    
    /**
     * Genera una versión simplificada del comprobante para mostrar en diálogos.
     */
    public String generarTextoSimplificado() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("╔═══════════════════════════════════════════╗\n");
        sb.append("║       COMPROBANTE DE TRANSACCIÓN          ║\n");
        sb.append("╚═══════════════════════════════════════════╝\n\n");
        
        sb.append(String.format("N° Comprobante: %s\n", numeroComprobante));
        sb.append(String.format("Fecha: %s\n", fechaHora.format(formatoFecha)));
        sb.append(String.format("Operación: %s\n", tipoOperacion));
        sb.append(String.format("Cuenta: %s\n", numeroCuenta));
        
        if (cuentaDestino != null && !cuentaDestino.isEmpty()) {
            sb.append(String.format("Destino: %s\n", cuentaDestino));
        }
        
        sb.append(String.format("Monto: Gs. %,.0f\n", monto));
        sb.append(String.format("Estado: %s\n", estado));
        
        return sb.toString();
    }
    
    // Getters y Setters
    
    public String getNumeroComprobante() {
        return numeroComprobante;
    }
    
    public String getTipoOperacion() {
        return tipoOperacion;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public String getCuentaDestino() {
        return cuentaDestino;
    }
    
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public double getMonto() {
        return monto;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }
}
