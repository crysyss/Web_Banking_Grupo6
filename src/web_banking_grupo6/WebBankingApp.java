package web_banking_grupo6;

import web_banking_grupo6.vista.login.LoginWindow;

/**
 * Clase principal del sistema Web Banking.
 * Inicializa y lanza la aplicación con el sistema de login.
 */
public class WebBankingApp {
    
    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Lanzar ventana de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }
}
