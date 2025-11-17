package web_banking_grupo6.vista.principal.main;

import web_banking_grupo6.vista.principal.DefaultForm;
import web_banking_grupo6.vista.principal.HomeForm;
import java.awt.Component;
import web_banking_grupo6.vista.principal.menu.MenuEvent;
import web_banking_grupo6.vista.componentes.CloseButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author RAVEN
 */
public class Main extends javax.swing.JFrame {

    // Variables para arrastrar la ventana
    private int mouseX, mouseY;
    
    /**
     * Creates new form Main
     */
    
    public Main() {
        initComponents();
        agregarBotonCerrar();
        agregarListenersParaArrastrar();
        
        // Mostrar el formulario inicial primero
        showForm(new HomeForm());
        
        // Luego configurar el evento del menú
        menu1.setEvent(new MenuEvent() {
            @Override
            public void selected(int index, int subIndex) {
                switch (index) {
                    case 0: // Inicio
                        showForm(new HomeForm());
                        break;
                    case 1: // Mi Cuenta
                        if (subIndex == 1) {
                            showForm(new DefaultForm("Consultar Saldo"));
                        } else if (subIndex == 2) {
                            showForm(new DefaultForm("Historial de Transacciones"));
                        }
                        break;
                    case 2: // Transferencias
                        if (subIndex == 1) {
                            showForm(new DefaultForm("Realizar Transferencia"));
                        } else if (subIndex == 2) {
                            showForm(new DefaultForm("Realizar Depósito"));
                        }
                        break;
                    case 3: // Pagos
                        if (subIndex == 1) {
                            showForm(new DefaultForm("Pago de Servicios"));
                        } else if (subIndex == 2) {
                            showForm(new DefaultForm("Pago de Tarjetas"));
                        }
                        break;
                    case 4: // Perfil
                        if (subIndex == 1) {
                            showForm(new DefaultForm("Mis Datos"));
                        } else if (subIndex == 2) {
                            // Cerrar sesión
                            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                                Main.this,
                                "¿Está seguro que desea cerrar sesión?",
                                "Cerrar Sesión",
                                javax.swing.JOptionPane.YES_NO_OPTION
                            );
                            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                                Main.this.dispose();
                                // Volver a la ventana de login
                                java.awt.EventQueue.invokeLater(() -> {
                                    web_banking_grupo6.vista.login.LoginWindow loginWindow = 
                                        new web_banking_grupo6.vista.login.LoginWindow();
                                    loginWindow.setVisible(true);
                                });
                            }
                        }
                        break;
                }
            }
        });
    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }
    
    private void agregarBotonCerrar() {
        final CloseButton closeButton = new CloseButton();
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirmar antes de cerrar
                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    Main.this,
                    "¿Está seguro que desea salir?",
                    "Confirmar Salida",
                    javax.swing.JOptionPane.YES_NO_OPTION
                );
                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        // Agregar el botón directamente al contentPane usando JLayeredPane
        javax.swing.JLayeredPane layeredPane = getRootPane().getLayeredPane();
        layeredPane.add(closeButton, javax.swing.JLayeredPane.POPUP_LAYER);
        
        // Posicionar el botón cuando la ventana tenga su tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Posicionar en la esquina superior derecha
                int x = getWidth() - 50;  // 50 = 40 (ancho del botón) + 10 (margen)
                int y = 10;  // Margen superior
                closeButton.setBounds(x, y, 40, 40);
            }
        });
        
        // Posición inicial
        closeButton.setBounds(getWidth() - 50, 10, 40, 40);
    }
    
    private void agregarListenersParaArrastrar() {
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
            }
        });
        
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu1 = new web_banking_grupo6.vista.principal.menu.Menu();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 163, 163)));

        body.setBackground(new java.awt.Color(245, 245, 245));
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JPanel jPanel1;
    private web_banking_grupo6.vista.principal.menu.Menu menu1;
    // End of variables declaration//GEN-END:variables
}
