package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

public class ComponentePrecio extends JPanel {

    private final JTextField txtPrecio;

    public ComponentePrecio() {

        setLayout(new BorderLayout(10, 0));

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setPreferredSize(new Dimension(120, 30));

        txtPrecio = new JTextField();
        txtPrecio.setPreferredSize(new Dimension(250, 30));

        add(lblPrecio, BorderLayout.WEST);
        add(txtPrecio, BorderLayout.CENTER);
    }

    public ComponentePrecio(double precio) {
        this();
        txtPrecio.setText(String.valueOf(precio));
    }

    public String getTexto() {
        return txtPrecio.getText().trim();
    }

    private double parsearPrecio() {
        String texto = getTexto();

        if (!texto.matches("(?:[0-9]+(?:[.,][0-9]*)?|[.,][0-9]+)")) {
            throw new NumberFormatException("Formato decimal inválido");
        }

        return Double.parseDouble(texto.replace(',', '.'));
    }

    public boolean esValido() {
        try {
            double precio = parsearPrecio();
            return Double.isFinite(precio) && precio > 0 && precio <= 1000000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double getPrecio() {
        return parsearPrecio();
    }

    public boolean validacionPrecio() {
        if (esValido()) return true;

        JOptionPane.showMessageDialog(
                this,
                "Ingrese un precio mayor que cero y menor/igual que un millón. Puede usar coma o punto decimal.",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE
        );

        txtPrecio.requestFocusInWindow();
        return false;
    }

    public void limpiarPrecio() {
        limpiar();
    }

    public void limpiar() {
        txtPrecio.setText("");
    }
}