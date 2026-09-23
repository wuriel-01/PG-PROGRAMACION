package components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComponenteNombre extends JPanel {

    private JTextField CampoNombre;

    public ComponenteNombre() {
          

        CampoNombre = new JTextField();
        setLayout(new GridLayout(1, 2, 10, 0));
        add(new JLabel("Nombre:"));
        add(CampoNombre);
    }

    public ComponenteNombre(String nombre) {

        this();

        CampoNombre.setText(nombre);
    }

    public String getNombre() {
        return CampoNombre.getText().trim();
    }

    public void limpiarNombre() {
    CampoNombre.setText("");
}

    public boolean validacionNombre() {

        String nombre = CampoNombre.getText().trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del producto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            CampoNombre.requestFocus();

            return false;
        }

        return true;
    }
}