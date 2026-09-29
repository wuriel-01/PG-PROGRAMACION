package components;

import java.awt.FlowLayout;
import javax.swing.*;

public class ComponenteNombre extends JPanel {
    private final JTextField txtNombre;

    public ComponenteNombre() {
        // Establecer alineación a la izquierda
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        add(txtNombre);
    }

    public ComponenteNombre(String nombre) {
        this();
        txtNombre.setText(nombre == null ? "" : nombre);
    }

    public String getTexto() {
        return txtNombre.getText().trim();
    }

    public boolean esValido() {
        return !getTexto().isEmpty();
    }

    public String getNombre() {
        return getTexto();
    }

    public boolean validacionNombre() {
      String  nombre= getTexto();
        if (nombre.length() < 2 || nombre.length() > 30) {
            JOptionPane.showMessageDialog(this,
                    "El nombre debe tener entre 2 y 30 dígitos.",
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }
        return true;
    }

    public void limpiarNombre() {
        limpiar();
    }

    public void limpiar() {
        txtNombre.setText("");
    }
}
