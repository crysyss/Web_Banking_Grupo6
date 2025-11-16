package web_banking_grupo6;

/**
 * Gestor de autenticación del sistema.
 * Valida credenciales de clientes y controla el acceso al sistema.
 */
public class LoginManager {
    
    private ClienteRepository repositorio;
    private Cliente clienteActual;
    
    public LoginManager() {
        this.repositorio = new ClienteRepository();
        this.clienteActual = null;
    }
    
    /**
     * Intenta autenticar un cliente con sus credenciales.
     * @param documento DNI/documento del cliente
     * @param clave Contraseña del cliente
     * @return ResultadoLogin con el estado de la autenticación
     */
    public ResultadoLogin autenticar(String documento, String clave) {
        // Validar que los campos no estén vacíos
        if (documento == null || documento.trim().isEmpty()) {
            return new ResultadoLogin(false, "Debe ingresar su documento");
        }
        
        if (clave == null || clave.trim().isEmpty()) {
            return new ResultadoLogin(false, "Debe ingresar su contraseña");
        }
        
        // Buscar el cliente en el repositorio
        Cliente cliente = repositorio.buscarPorDocumento(documento.trim());
        
        if (cliente == null) {
            return new ResultadoLogin(false, "Cliente no encontrado");
        }
        
        // Verificar si la cuenta está bloqueada
        if (cliente.isEstaBloqueada()) {
            return new ResultadoLogin(false, 
                "Cuenta bloqueada por múltiples intentos fallidos. Contacte al banco.");
        }
        
        // Validar la contraseña
        if (cliente.validarClave(clave)) {
            this.clienteActual = cliente;
            return new ResultadoLogin(true, 
                "Bienvenido " + cliente.getNombreCompleto());
        } else {
            int intentosRestantes = 5 - cliente.getIntentosFallidos();
            if (intentosRestantes > 0) {
                return new ResultadoLogin(false, 
                    "Contraseña incorrecta. Le quedan " + intentosRestantes + " intentos.");
            } else {
                return new ResultadoLogin(false, 
                    "Cuenta bloqueada por múltiples intentos fallidos.");
            }
        }
    }
    
    /**
     * Cierra la sesión actual.
     */
    public void cerrarSesion() {
        this.clienteActual = null;
    }
    
    /**
     * Obtiene el cliente que inició sesión actualmente.
     */
    public Cliente getClienteActual() {
        return clienteActual;
    }
    
    /**
     * Verifica si hay una sesión activa.
     */
    public boolean haySesionActiva() {
        return clienteActual != null;
    }
    
    /**
     * Clase interna para devolver el resultado del login.
     */
    public static class ResultadoLogin {
        private final boolean exitoso;
        private final String mensaje;
        
        public ResultadoLogin(boolean exitoso, String mensaje) {
            this.exitoso = exitoso;
            this.mensaje = mensaje;
        }
        
        public boolean isExitoso() {
            return exitoso;
        }
        
        public String getMensaje() {
            return mensaje;
        }
    }
}
