package components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComponentePrecio extends JPanel{
    private JTextField CampoPrecio;

    public ComponentePrecio (){
       
        CampoPrecio = new JTextField();
        setLayout(new GridLayout(1, 2, 10, 0));
        add(new JLabel("Precio:"));
        add(CampoPrecio);
    }

    public ComponentePrecio(double precio){
        this();
    CampoPrecio.setText(String.valueOf(precio));
    }

      public double getPrecio() {
       return Double.parseDouble(CampoPrecio.getText().trim());
    }

    public void limpiarPrecio(){
        CampoPrecio.setText("");
    }
     

    double precio;
     public boolean validacionPrecio() {
        try {

            precio = Double.parseDouble(CampoPrecio.getText().trim());

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El precio debe ser un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            CampoPrecio.requestFocus();
            return false;
        }


           if (precio <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "El precio debe ser mayor que cero.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
             CampoPrecio.requestFocus();
            return false;
        }
        return true;
    }
}
