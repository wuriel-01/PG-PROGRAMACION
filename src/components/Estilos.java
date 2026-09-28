package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Estilos {

    public static final Color FONDO = new Color(245, 247, 250);
    public static final Color PANEL = Color.WHITE;
    public static final Color PRIMARIO = new Color(37, 99, 235);
    public static final Color EXITO = new Color(22, 163, 74);
    public static final Color PELIGRO = new Color(220, 38, 38);
    public static final Color SECUNDARIO = new Color(100, 116, 139);
    public static final Color MOVIMIENTOS = new Color(124, 58, 237);
    public static final Color TEXTO = new Color(30, 41, 59);
    public static final Color BORDE = new Color(226, 232, 240);

    public static JButton boton(String texto, Color color) {
        JButton boton = new JButton(texto);

        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setPreferredSize(new Dimension(100, 32));

        return boton;
    }

    public static JLabel titulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(TEXTO);
        return label;
    }

    public static JLabel encabezado(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(TEXTO);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return label;
    }
}