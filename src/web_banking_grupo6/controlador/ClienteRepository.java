package web_banking_grupo6.controlador;

import web_banking_grupo6.modelo.Cliente;
import web_banking_grupo6.modelo.Cuenta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio de clientes del sistema.
 * Inicializa clientes de prueba para probar la funcionalidad del login.
 * Implementa Singleton para evitar múltiples inicializaciones.
 */
public class ClienteRepository {
    
    private static ClienteRepository instancia;
    private Map<String, Cliente> clientes;
    
    private ClienteRepository() {
        this.clientes = new HashMap<>();
        inicializarClientesDePrueba();
    }
    
    public static ClienteRepository getInstancia() {
        if (instancia == null) {
            instancia = new ClienteRepository();
        }
        return instancia;
    }
    
    /**
     * Inicializa clientes de prueba con cuentas y saldos.
     */
    private void inicializarClientesDePrueba() {
        // Usuario de prueba: Luis More
        Cliente cliente1 = new Cliente(
            "123456",
            "Luis More",
            "admin",
            "987654321",
            "luis.more@email.com"
        );
        Cuenta cuenta1 = new Cuenta("CTA-001-001", 10000.00, "Caja de Ahorro");
        cliente1.setCuenta(cuenta1);
        clientes.put(cliente1.getDocumento(), cliente1);
        
        System.out.println("\nCredencial de prueba:");
        System.out.println("- Documento: " + cliente1.getDocumento() + 
                         " | Clave: " + cliente1.getClave() + 
                         " | " + cliente1.getNombreCompleto() +
                         " | Saldo: $" + cliente1.getCuenta().getFondos());
        System.out.println("========================================\n");
    }
    
    /**
     * Busca un cliente por su número de documento.
     */
    public Cliente buscarPorDocumento(String documento) {
        return clientes.get(documento);
    }
    
    /**
     * Busca un cliente por su email.
     */
    public Cliente buscarPorEmail(String email) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        return null;
    }
    
    /**
     * Agrega un nuevo cliente al repositorio.
     */
    public boolean agregarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getDocumento())) {
            return false; // Ya existe un cliente con ese documento
        }
        clientes.put(cliente.getDocumento(), cliente);
        return true;
    }
    
    /**
     * Obtiene todos los clientes.
     */
    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(clientes.values());
    }
    
    /**
     * Obtiene la cantidad de clientes registrados.
     */
    public int cantidadClientes() {
        return clientes.size();
    }
}
