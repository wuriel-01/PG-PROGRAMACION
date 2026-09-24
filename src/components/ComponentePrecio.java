package components;

import java.awt.FlowLayout;
import javax.swing.*;

public class ComponentePrecio extends JPanel {
    private final JTextField txtPrecio;

    public ComponentePrecio() {
        // Establecer alineación a la izquierda
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Precio:"));
        txtPrecio = new JTextField(10);
        add(txtPrecio);
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
        // Acepta coma decimal además del punto.
        return Double.parseDouble(texto.replace(',', '.'));
    }

    public boolean esValido() {
        try {
            double precio = parsearPrecio();
            return Double.isFinite(precio) && precio > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double getPrecio() {
        return parsearPrecio();
    }

    public boolean validacionPrecio() {
        if (esValido()) return true;
        JOptionPane.showMessageDialog(this, "Ingrese un precio mayor que cero. Puede usar coma o punto decimal.", "Error de validación", JOptionPane.ERROR_MESSAGE);
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