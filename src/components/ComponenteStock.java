package components;

import java.awt.FlowLayout;
import javax.swing.*;

public class ComponenteStock extends JPanel {
    private JTextField txtStock;

    
    public ComponenteStock() {
        this(0); 
    }

   
    public ComponenteStock(int stock) {
        
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
        if (getTexto().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El stock no puede estar vacío.");
            return false;
        }

        try {
            int val = Integer.parseInt(getTexto());

            if (val <= 0) {
                JOptionPane.showMessageDialog(this, "El stock debe ser mayor a 0.");
                return false;
            }

            if (val > 100000) {
                JOptionPane.showMessageDialog(this, "El stock no puede superar las 100.000 unidades.");
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El stock debe ser un número entero válido.");
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