package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ComponenteCategoria extends JPanel {

    private JComboBox<String> cmbCategoria;

    public ComponenteCategoria() {

        setLayout(new BorderLayout(10, 0));

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setPreferredSize(new Dimension(120, 30));

        cmbCategoria = new JComboBox<>();
        cmbCategoria.setPreferredSize(new Dimension(250, 30));

        cmbCategoria.addItem("Almacén");
        cmbCategoria.addItem("Bebidas");
        cmbCategoria.addItem("Limpieza");
        cmbCategoria.addItem("Verduleria");
        cmbCategoria.addItem("Otros");

        add(lblCategoria, BorderLayout.WEST);
        add(cmbCategoria, BorderLayout.CENTER);
    }

    public ComponenteCategoria(String categoriaSeleccionada) {
        this();
        cmbCategoria.setSelectedItem(categoriaSeleccionada);
    }

    public String getCategoria() {
        Object seleccion = cmbCategoria.getSelectedItem();
        return seleccion == null ? "" : seleccion.toString();
    }

    public boolean validacionCategoria() {

        if (getCategoria().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una categoría.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            cmbCategoria.requestFocusInWindow();
            return false;
        }

        return true;
    }

    public void limpiarCategoria() {
        cmbCategoria.setSelectedIndex(0);
    }
}