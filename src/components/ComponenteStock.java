package components;

import java.awt.FlowLayout;
import javax.swing.*;

public class ComponenteStock extends JPanel {
    private JTextField txtStock;

    // Constructor sin parámetros
    public ComponenteStock() {
        this(0);
    }

    // Constructor con parámetro (para cuando se edita un producto)
    public ComponenteStock(int stock) {
        // Establecer alineación a la izquierda
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Stock:"));
        txtStock = new JTextField(15);
        txtStock.setText(String.valueOf(stock));
        add(txtStock);
    }

    public String getTexto() {
        return txtStock.getText().trim();
    }

    public int getStock() {
        try {
            return Integer.parseInt(getTexto());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean validacionStock() {
        try {
            int val = Integer.parseInt(getTexto());
            return val >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void limpiar() {
        txtStock.setText("");
    }

    public void limpiarStock() {
        limpiar();
    }
}