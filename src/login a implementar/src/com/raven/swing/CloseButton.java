package com.raven.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Botón de cerrar personalizado con efecto hover rojo.
 * Se pinta de rojo al pasar el cursor y vuelve a su color original al quitarlo.
 */
public class CloseButton extends JButton {
    
    private boolean hover = false;
    private final Color colorNormal = new Color(240, 240, 240);
    private final Color colorHover = new Color(220, 53, 69); // Rojo
    private final Color colorTextoNormal = new Color(100, 100, 100);
    private final Color colorTextoHover = Color.WHITE;
    
    public CloseButton() {
        setText("✕");
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(new java.awt.Font("Arial", 1, 20));
        
        // Agregar listeners para el efecto hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dibujar el fondo del botón
        if (hover) {
            g2.setColor(colorHover);
            setForeground(colorTextoHover);
        } else {
            g2.setColor(colorNormal);
            setForeground(colorTextoNormal);
        }
        
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.dispose();
        
        super.paintComponent(g);
    }
}
