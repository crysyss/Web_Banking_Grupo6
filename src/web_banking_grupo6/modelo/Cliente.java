package web_banking_grupo6.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un cliente del sistema Web Banking.
 * Contiene su información personal y relaciones con sus cuentas y tarjetas.
 * (Me parece que nombreCompleto es mejor que nombre y apellido por separado)
 */
public class Cliente {
    private String documento;
    private String nombreCompleto;
    private String clave;
    private String telefono;
    private String email;
    
    // Un cliente tiene una cuenta bancaria principal
    private Cuenta cuenta;
    
    // Un cliente puede tener múltiples tarjetas (crédito, débito, etc.)
    private List<Tarjeta> tarjetas;
    
    // Control de seguridad para el login
    private int intentosFallidos;
    private boolean estaBloqueada;
    
    public Cliente(String documento, String nombreCompleto, String clave, String telefono, String email) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.clave = clave;
        this.telefono = telefono;
        this.email = email;
        this.tarjetas = new ArrayList<>();
        this.intentosFallidos = 0;
        this.estaBloqueada = false;
    }
    
    /**
     * Valida si la clave ingresada coincide con la del cliente.
     * Si falla 5 veces consecutivas, bloquea la cuenta automáticamente.
     */
    public boolean validarClave(String claveIngresada) {
        if (estaBloqueada) {
            return false;
        }
        
        if (this.clave.equals(claveIngresada)) {
            // Reset de intentos fallidos al hacer login exitoso
            intentosFallidos = 0;
            return true;
        } else {
            intentosFallidos++;
            if (intentosFallidos >= 5) {
                estaBloqueada = true;
            }
            return false;
        }
    }
    
    // Getters y Setters
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Cuenta getCuenta() {
        return cuenta;
    }
    
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }
    
    public void agregarTarjeta(Tarjeta tarjeta) {
        this.tarjetas.add(tarjeta);
    }
    
    public int getIntentosFallidos() {
        return intentosFallidos;
    }
    
    public boolean isEstaBloqueada() {
        return estaBloqueada;
    }
    
    public void setEstaBloqueada(boolean estaBloqueada) {
        this.estaBloqueada = estaBloqueada;
    }
}
