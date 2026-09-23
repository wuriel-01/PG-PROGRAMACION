package components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComponenteStock extends JPanel{
    private JTextField CampoStock;

    public ComponenteStock(){
        CampoStock = new JTextField();
        setLayout(new GridLayout(1, 2, 10, 0));
        add(new JLabel("Stock:"));
        add(CampoStock);

        
    }

    public ComponenteStock(int stock){
        this();
        CampoStock.setText(String.valueOf(stock));
    }

    public int getStock() {
        return Integer.parseInt(CampoStock.getText().trim());
    }

    public void limpiarStock(){
        CampoStock.setText("");
    }
    
    int stock;
    public boolean validacionStock (){
        try {

              stock = Integer.parseInt(CampoStock.getText().trim());

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El stock debe ser un número entero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
           CampoStock.requestFocus();  
            return false;
        }
       


        if (stock < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "El stock no puede ser negativo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            CampoStock.requestFocus();  
            return false;
        }
         return true;
    }
}
