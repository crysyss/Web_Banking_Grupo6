package web_banking_grupo6.modelo;

/**
 * Representa un servicio que puede ser pagado a través del sistema.
 * Ej: ANDE, ESSAP, Cuota FPUNA, etc.
 */
public class Servicio {
    private String codigoServicio;
    private String nombreServicio;
    private String empresa;
    private String descripcion;
    
    public Servicio(String codigoServicio, String nombreServicio, String empresa, String descripcion) {
        this.codigoServicio = codigoServicio;
        this.nombreServicio = nombreServicio;
        this.empresa = empresa;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    
    public String getCodigoServicio() {
        return codigoServicio;
    }
    
    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }
    
    public String getNombreServicio() {
        return nombreServicio;
    }
    
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
    
    public String getEmpresa() {
        return empresa;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return nombreServicio + " - " + empresa;
    }
}
