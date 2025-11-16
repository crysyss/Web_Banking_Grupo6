package web_banking_grupo6;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la cuenta bancaria de un cliente.
 * Maneja el saldo (fondos) y el historial de transacciones.
 */
public class Cuenta {
    private String numeroCuenta;
    private double fondos;  // Saldo actual de la cuenta
    private String tipoCuenta;  // "Caja de Ahorro", "Cuenta Corriente", etc.
    
    // Historial de todas las operaciones realizadas
    private List<Transaccion> transacciones;
    
    public Cuenta(String numeroCuenta, double fondosInicial, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.fondos = fondosInicial;
        this.tipoCuenta = tipoCuenta;
        this.transacciones = new ArrayList<>();
    }
    
    /**
     * Valida si hay fondos suficientes para realizar una operación.
     * Esta validación es crítica antes de cualquier pago.
     */
    public boolean validarFondos(double monto) {
        if (monto <= 0) {
            return false;
        }
        return fondos >= monto;
    }
    
    /**
     * Debita un monto de la cuenta.
     * IMPORTANTE: Siempre validar fondos ANTES de llamar a este método.
     */
    public void debitar(double monto) {
        if (validarFondos(monto)) {
            fondos -= monto;
        } else {
            throw new IllegalStateException("Fondos insuficientes para debitar");
        }
    }
    
    /**
     * Acredita un monto a la cuenta (depósitos, devoluciones, etc.)
     */
    public void acreditar(double monto) {
        if (monto > 0) {
            fondos += monto;
        }
    }
    
    /**
     * Registra una transacción en el historial de la cuenta.
     */
    public void registrarTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
    }
    
    // Getters y Setters
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    public double getFondos() {
        return fondos;
    }
    
    public void setFondos(double fondos) {
        this.fondos = fondos;
    }
    
    public String getTipoCuenta() {
        return tipoCuenta;
    }
    
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}
