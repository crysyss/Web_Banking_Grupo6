package web_banking_grupo6.ui.login;

import web_banking_grupo6.ui.components.Button;
import web_banking_grupo6.ui.components.MyPasswordField;
import web_banking_grupo6.ui.components.MyTextField;
import web_banking_grupo6.LoginManager;
import web_banking_grupo6.LoginManager.ResultadoLogin;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private LoginManager loginManager;
    private MyTextField txtDocumentoLogin;
    private MyPasswordField txtPasswordLogin;
    
    public PanelLoginAndRegister() {
        initComponents();
        loginManager = new LoginManager();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Crear Cuenta");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/resources/icons/user.png")));
        txtUser.setHint("Nombre");
        register.add(txtUser, "w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/resources/icons/mail.png")));
        txtEmail.setHint("Correo Electrónico");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/resources/icons/pass.png")));
        txtPass.setHint("Contraseña");
        register.add(txtPass, "w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("REGISTRARSE");
        register.add(cmd, "w 40%, h 40");
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Iniciar Sesión");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        

        txtDocumentoLogin = new MyTextField();
        txtDocumentoLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/resources/icons/user.png")));
        txtDocumentoLogin.setHint("Documento");
        login.add(txtDocumentoLogin, "w 60%");
        
        // Campo de contraseña
        txtPasswordLogin = new MyPasswordField();
        txtPasswordLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/resources/icons/pass.png")));
        txtPasswordLogin.setHint("Contraseña");
        login.add(txtPasswordLogin, "w 60%");
        
        JButton cmdForget = new JButton("¿Olvidaste tu contraseña?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("INICIAR SESIÓN");
        
        // Agregar evento al botón de login
        cmd.addActionListener(e -> {
            String documento = txtDocumentoLogin.getText().trim();
            String password = String.valueOf(txtPasswordLogin.getPassword());
            
            ResultadoLogin resultado = loginManager.autenticar(documento, password);
            
            if (resultado.isExitoso()) {
                JOptionPane.showMessageDialog(this, 
                    resultado.getMensaje(), 
                    "Login Exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Aquí podrías abrir la ventana principal del sistema
                System.out.println("Usuario autenticado: " + loginManager.getClienteActual().getNombreCompleto());
            } else {
                JOptionPane.showMessageDialog(this, 
                    resultado.getMensaje(), 
                    "Error de Autenticación", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        login.add(cmd, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
