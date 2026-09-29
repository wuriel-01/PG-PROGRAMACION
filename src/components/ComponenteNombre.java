package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

public class ComponenteNombre extends JPanel {

    private final JTextField txtNombre;

    public ComponenteNombre() {

        setLayout(new BorderLayout(10, 0));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setPreferredSize(new Dimension(120, 30));

        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(250, 30));

        add(lblNombre, BorderLayout.WEST);
        add(txtNombre, BorderLayout.CENTER);
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
        String nombre = getTexto();

        if (nombre.length() < 2 || nombre.length() > 30) {
            JOptionPane.showMessageDialog(this,
                    "El nombre debe tener entre 2 y 30 caracteres.",
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