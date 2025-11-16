package web_banking_grupo6;

/**
 * Clase de prueba para verificar el funcionamiento del sistema de login.
 * Ejecuta diferentes casos de prueba de autenticación.
 */
public class PruebaLogin {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   PRUEBAS DEL SISTEMA DE LOGIN");
        System.out.println("========================================\n");
        
        LoginManager loginManager = new LoginManager();
        
        // Test 1: Login exitoso
        System.out.println("--- Test 1: Login Exitoso ---");
        LoginManager.ResultadoLogin resultado1 = loginManager.autenticar("12345678", "juan123");
        System.out.println("Resultado: " + resultado1.getMensaje());
        System.out.println("Estado: " + (resultado1.isExitoso() ? "✓ EXITOSO" : "✗ FALLIDO"));
        if (resultado1.isExitoso()) {
            System.out.println("Cliente logueado: " + loginManager.getClienteActual().getNombreCompleto());
            System.out.println("Saldo: $" + loginManager.getClienteActual().getCuenta().getFondos());
        }
        loginManager.cerrarSesion();
        System.out.println();
        
        // Test 2: Contraseña incorrecta
        System.out.println("--- Test 2: Contraseña Incorrecta ---");
        LoginManager.ResultadoLogin resultado2 = loginManager.autenticar("12345678", "wrongpass");
        System.out.println("Resultado: " + resultado2.getMensaje());
        System.out.println("Estado: " + (resultado2.isExitoso() ? "✓ EXITOSO" : "✗ FALLIDO (esperado)"));
        System.out.println();
        
        // Test 3: Cliente no existe
        System.out.println("--- Test 3: Cliente No Existe ---");
        LoginManager.ResultadoLogin resultado3 = loginManager.autenticar("99999999", "anypass");
        System.out.println("Resultado: " + resultado3.getMensaje());
        System.out.println("Estado: " + (resultado3.isExitoso() ? "✓ EXITOSO" : "✗ FALLIDO (esperado)"));
        System.out.println();
        
        // Test 4: Campos vacíos
        System.out.println("--- Test 4: Campos Vacíos ---");
        LoginManager.ResultadoLogin resultado4 = loginManager.autenticar("", "");
        System.out.println("Resultado: " + resultado4.getMensaje());
        System.out.println("Estado: " + (resultado4.isExitoso() ? "✓ EXITOSO" : "✗ FALLIDO (esperado)"));
        System.out.println();
        
        // Test 5: Múltiples intentos fallidos (bloqueo)
        System.out.println("--- Test 5: Bloqueo por Múltiples Intentos ---");
        LoginManager loginManager2 = new LoginManager();
        for (int i = 1; i <= 6; i++) {
            System.out.println("Intento " + i + ":");
            LoginManager.ResultadoLogin resultado = loginManager2.autenticar("87654321", "wrongpass");
            System.out.println("  → " + resultado.getMensaje());
        }
        System.out.println();
        
        // Test 6: Login con diferentes clientes
        System.out.println("--- Test 6: Otros Clientes ---");
        LoginManager loginManager3 = new LoginManager();
        
        String[][] clientes = {
            {"87654321", "maria456", "María López"},
            {"45678912", "carlos789", "Carlos Ramírez"},
            {"78912345", "ana2024", "Ana Martínez"},
            {"32165498", "pedro555", "Pedro González"}
        };
        
        for (String[] cliente : clientes) {
            LoginManager.ResultadoLogin resultado = loginManager3.autenticar(cliente[0], cliente[1]);
            if (resultado.isExitoso()) {
                System.out.println("✓ " + cliente[2] + " - Autenticado correctamente");
            } else {
                System.out.println("✗ " + cliente[2] + " - Error: " + resultado.getMensaje());
            }
            loginManager3.cerrarSesion();
        }
        
        System.out.println("\n========================================");
        System.out.println("   PRUEBAS COMPLETADAS");
        System.out.println("========================================");
    }
}
