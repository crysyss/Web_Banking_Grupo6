package web_banking_grupo6.controlador;

import web_banking_grupo6.modelo.Cliente;
import web_banking_grupo6.modelo.Cuenta;

/**
 * Gestor de autenticación del sistema.
 * Valida credenciales de clientes y controla el acceso al sistema.
 */
public class GestorLogin {
    
    private ClienteRepository repositorio;
    private Cliente clienteActual;
    
    public GestorLogin() {
        this.repositorio = ClienteRepository.getInstancia();
        this.clienteActual = null;
    }
    
    /**
     * Intenta autenticar un cliente con sus credenciales.
     * @param documento documento del cliente
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
     * Registra un nuevo cliente en el sistema con todas las validaciones necesarias.
     */
    public ResultadoLogin registrarCliente(String nombreCompleto, String documento, String email, String clave) {
        // Validar que los campos no estén vacíos
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            return new ResultadoLogin(false, "Debe ingresar su nombre completo");
        }
        
        if (documento == null || documento.trim().isEmpty()) {
            return new ResultadoLogin(false, "Debe ingresar su documento");
        }
        
        if (email == null || email.trim().isEmpty()) {
            return new ResultadoLogin(false, "Debe ingresar su correo electrónico");
        }
        
        if (clave == null || clave.trim().isEmpty()) {
            return new ResultadoLogin(false, "Debe ingresar una contraseña");
        }
        
        // Validar formato de email
        if (!esEmailValido(email.trim())) {
            return new ResultadoLogin(false, "El formato del correo electrónico no es válido");
        }
        
        // Validar longitud mínima de contraseña
        if (clave.length() < 6) {
            return new ResultadoLogin(false, "La contraseña debe tener al menos 6 caracteres");
        }
        
        // Validar que el documento solo contenga números
        if (!documento.trim().matches("\\d+")) {
            return new ResultadoLogin(false, "El documento debe contener solo números");
        }
        
        // Verificar que no exista un cliente con ese documento
        if (repositorio.buscarPorDocumento(documento.trim()) != null) {
            return new ResultadoLogin(false, "Ya existe un cliente registrado con ese documento");
        }
        
        // Verificar que no exista un cliente con ese email
        if (repositorio.buscarPorEmail(email.trim()) != null) {
            return new ResultadoLogin(false, "Ya existe un cliente registrado con ese correo electrónico");
        }
        
        // Crear el nuevo cliente
        Cliente nuevoCliente = new Cliente(
            documento.trim(),
            nombreCompleto.trim(),
            clave,
            "", // Teléfono vacío por ahora
            email.trim()
        );
        
        // Crear una cuenta inicial para el cliente con saldo 0
        String numeroCuenta = generarNumeroCuenta();
        Cuenta cuentaNueva = new Cuenta(numeroCuenta, 0.0, "Caja de Ahorro");
        nuevoCliente.setCuenta(cuentaNueva);
        
        // Agregar el cliente al repositorio
        boolean agregado = repositorio.agregarCliente(nuevoCliente);
        
        if (agregado) {
            return new ResultadoLogin(true, 
                "Cuenta creada exitosamente. Ya puede iniciar sesión.");
        } else {
            return new ResultadoLogin(false, 
                "Error al crear la cuenta. Por favor intente nuevamente.");
        }
    }
    
    /**
     * Valida si un email tiene un formato válido.
     */
    private boolean esEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    
    /**
     * Genera un número de cuenta único basado en la cantidad de clientes.
     */
    private String generarNumeroCuenta() {
        int cantidadClientes = repositorio.cantidadClientes();
        return String.format("CTA-001-%03d", cantidadClientes + 1);
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
