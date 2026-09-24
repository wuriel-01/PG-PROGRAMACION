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
        if (esValido()) return true;
        JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error de validación", JOptionPane.ERROR_MESSAGE);
        txtNombre.requestFocusInWindow();
        return false;
    }

    public void limpiarNombre() {
        limpiar();
    }

    public void limpiar() {
        txtNombre.setText("");
    }
}
