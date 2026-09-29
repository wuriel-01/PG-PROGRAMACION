package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComponenteCodigoBarras extends JPanel {

    private JTextField txtCodigoBarras;

    public ComponenteCodigoBarras() {
        this("");
    }

    public ComponenteCodigoBarras(String codigo) {

        setLayout(new BorderLayout(10, 0));

        JLabel lblCodigo = new JLabel("Código de Barras:");
        lblCodigo.setPreferredSize(new Dimension(120, 30));

        txtCodigoBarras = new JTextField();
        txtCodigoBarras.setPreferredSize(new Dimension(250, 30));

        if (codigo != null) {
            txtCodigoBarras.setText(codigo);
        }

        add(lblCodigo, BorderLayout.WEST);
        add(txtCodigoBarras, BorderLayout.CENTER);
    }

    public String getTexto() {
        return txtCodigoBarras.getText().trim();
    }

    public String getCodigoBarras() {
        return getTexto();
    }

    public boolean validacionCodigoBarras() {
        String codigo = getTexto();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El código de barras no puede estar vacío.",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            txtCodigoBarras.requestFocus();
            return false;
        }

        if (!codigo.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "El código de barras debe contener únicamente números.",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            txtCodigoBarras.requestFocus();
            return false;
        }

        if (codigo.length() < 8 || codigo.length() > 14) {
            JOptionPane.showMessageDialog(this,
                    "El código de barras debe tener entre 8 y 14 dígitos.",
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