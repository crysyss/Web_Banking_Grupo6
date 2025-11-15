package web_banking_grupo6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio de clientes del sistema.
 * Inicializa clientes de prueba para probar la funcionalidad del login.
 */
public class ClienteRepository {
    
    private Map<String, Cliente> clientes;
    
    public ClienteRepository() {
        this.clientes = new HashMap<>();
        inicializarClientesDePrueba();
    }
    
    /**
     * Inicializa clientes de prueba con cuentas y saldos.
     */
    private void inicializarClientesDePrueba() {
        // Cliente 1: Juan Perez
        Cliente cliente1 = new Cliente(
            "12345678",
            "Juan Perez Garcia",
            "juan123",
            "987654321",
            "juan.perez@email.com"
        );
        Cuenta cuenta1 = new Cuenta("CTA-001-001", 5000.00, "Caja de Ahorro");
        cliente1.setCuenta(cuenta1);
        clientes.put(cliente1.getDocumento(), cliente1);
        
        // Cliente 2: María López
        Cliente cliente2 = new Cliente(
            "87654321",
            "Maria Lopez Rodriguez",
            "maria456",
            "912345678",
            "maria.lopez@email.com"
        );
        Cuenta cuenta2 = new Cuenta("CTA-001-002", 12500.50, "Cuenta Corriente");
        cliente2.setCuenta(cuenta2);
        clientes.put(cliente2.getDocumento(), cliente2);
        
        // Cliente 3: Carlos Ramírez
        Cliente cliente3 = new Cliente(
            "45678912",
            "Carlos Ramirez Torres",
            "carlos789",
            "956789123",
            "carlos.ramirez@email.com"
        );
        Cuenta cuenta3 = new Cuenta("CTA-001-003", 8750.25, "Caja de Ahorro");
        cliente3.setCuenta(cuenta3);
        clientes.put(cliente3.getDocumento(), cliente3);
        
        // Cliente 4: Ana Martinez
        Cliente cliente4 = new Cliente(
            "78912345",
            "Ana Martinez Silva",
            "ana2024",
            "923456789",
            "ana.martinez@email.com"
        );
        Cuenta cuenta4 = new Cuenta("CTA-001-004", 15000.00, "Cuenta Corriente");
        cliente4.setCuenta(cuenta4);
        clientes.put(cliente4.getDocumento(), cliente4);
        
        // Cliente 5: Pedro Gonzalez
        Cliente cliente5 = new Cliente(
            "32165498",
            "Pedro Gonzalez Vargas",
            "pedro555",
            "945678912",
            "pedro.gonzalez@email.com"
        );
        Cuenta cuenta5 = new Cuenta("CTA-001-005", 3200.75, "Caja de Ahorro");
        cliente5.setCuenta(cuenta5);
        clientes.put(cliente5.getDocumento(), cliente5);
        
        System.out.println("\nCredenciales para pruebas:");
        for (Cliente c : clientes.values()) {
            System.out.println("- Documento: " + c.getDocumento() + 
                             " | Clave: " + c.getClave() + 
                             " | " + c.getNombreCompleto() +
                             " | Saldo: $" + c.getCuenta().getFondos());
        }
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
