package components;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComponenteCodigoBarras extends JPanel {

    private JTextField txtCodigoBarras;

    // Constructor sin parámetros
    public ComponenteCodigoBarras() {
        this("");
    }

    // Constructor con parámetro (para cuando se edita un producto)
    public ComponenteCodigoBarras(String codigo) {
        // Establecer alineación a la izquierda
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Código de Barras:"));
        txtCodigoBarras = new JTextField(15);
        if (codigo != null) {
            txtCodigoBarras.setText(codigo);
        }
        add(txtCodigoBarras);
    }

    public String getTexto() {
        return txtCodigoBarras.getText().trim();
    }

    public String getCodigoBarras() {
        return getTexto();
    }

    // ========================================================
    // VALIDACIÓN COMPLETA Y CON MENSAJES DE ERROR AL USUARIO
    // ========================================================
    public boolean validacionCodigoBarras() {
        String codigo = getTexto();

        // 1. Validar que no esté vacío
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El código de barras no puede estar vacío.",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            txtCodigoBarras.requestFocus();
            return false;
        }

        // 2. Validar que contenga únicamente números
        if (!codigo.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "El código de barras debe contener únicamente números.",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            txtCodigoBarras.requestFocus();
            return false;
        }

        // 3. Validar longitud estándar (por ejemplo, entre 8 y 13 dígitos)
        if (codigo.length() < 8 || codigo.length() > 13) {
            JOptionPane.showMessageDialog(this,
                    "El código de barras debe tener entre 8 y 13 dígitos.",
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
            txtCodigoBarras.requestFocus();
            return false;
        }

        return true;
    }

    public void limpiar() {
        txtCodigoBarras.setText("");
    }
}